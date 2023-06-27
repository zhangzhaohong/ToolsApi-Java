package com.koala.service.custom.interceptor;

import com.koala.service.data.redis.RedisLockUtil;
import com.koala.service.utils.MD5Utils;
import com.koala.service.utils.RemoteIpUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.annotation.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;

import static com.koala.service.utils.RespUtil.formatRespDataWithCustomMsg;

/**
 * @author koala
 * @version 1.0
 * @date 2022/5/29 11:49
 * @description
 */
@Slf4j
@Component
public class FirewallInterceptor implements HandlerInterceptor {
    private static final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    private static final String LOCK_IP_URL_KEY = "lock_ip_";

    private static final String IP_URL_REQ_TIME = "ip_url_times_";

    private static final String IP_REQUEST_KEY = "ip_request_key_";

    private static final Integer LIMIT_TIMES = 5;

    private static final Integer IP_LOCK_TIME = 60 * 60;

    private static final Integer REQUEST_KEY_LOCK_TIME = 6 * 60 * 60;

    private static final String[] WHITE_LIST_HOST = new String[]{"127.0.0.1", "0:0:0:0:0:0:0:1", "192.168.2.250"};

    private static final String[] WHITE_LIST_PATH = new String[]{"/assets/", "/actuator", "/favicon.ico", "/error"};

    @Resource
    private RedisLockUtil redisLockUtil;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        final String ip = RemoteIpUtils.getRemoteIpByServletRequest(request, true);
        log.info("[FirewallInterceptor] request请求地址uri={},ip={}", request.getRequestURI(), ip);
        final String requestInfo = request.getHeader("request-info");
        if (StringUtils.hasLength(requestInfo)) {
            final String requestKey = request.getHeader("request-key");
            if (StringUtils.hasLength(requestKey)) {
                final String requestId = request.getHeader("request-id");
                final String requestTime = request.getHeader("request-time");
                if (requestInfo.equals(MD5Utils.md5(requestId + "mobile" + requestTime))) {
                    if (requestKey.equals(MD5Utils.md5(requestId + request.getRequestURI() + requestTime))) {
                        Boolean isNotOutdated = addIpRequestKeyLockAndCheckIsNotOutdated(requestKey, redisLockUtil);
                        if (isNotOutdated) {
                            log.info("[FirewallInterceptor] 客户端请求，key校验成功，自动放过={}", ip);
                            return true;
                        } else {
                            log.info("[FirewallInterceptor] 客户端请求，过期的key，访问被禁止={}", ip);
                            returnErrorPage(response, HttpStatus.FORBIDDEN.value(), formatRespDataWithCustomMsg(403, "key校验失败", null), "key校验失败");
                            return false;
                        }
                    } else {
                        log.info("[FirewallInterceptor] 客户端请求，key校验失败，访问被禁止={}", ip);
                        returnErrorPage(response, HttpStatus.FORBIDDEN.value(), formatRespDataWithCustomMsg(403, "key校验失败", null), "key校验失败");
                        return false;
                    }
                }
            }
        }
        for (String path : WHITE_LIST_PATH) {
            if (request.getRequestURI().startsWith(path)) {
                log.info("[FirewallInterceptor] 白名单请求，自动放过={}", ip);
                return true;
            }
        }
        if (Arrays.asList(WHITE_LIST_HOST).contains(ip)) {
            log.info("[FirewallInterceptor] 白名单IP，自动放过={}", ip);
            return true;
        }
        if (!redisLockUtil.getRedisStatus()) {
            log.info("[FirewallInterceptor] redis连接异常，自动放过={}", ip);
            return true;
        }
        if (checkIpIsLock(ip, redisLockUtil)) {
            log.info("[FirewallInterceptor] ip访问被禁止={}", ip);
            returnErrorPage(response, HttpStatus.FORBIDDEN.value(), formatRespDataWithCustomMsg(403, "非法访问，请1小时后重试", null), "非法访问，请1小时后重试");
            return false;
        }
        if (!addRequestTime(ip, request.getRequestURI(), redisLockUtil)) {
            log.info("[FirewallInterceptor] ip访问被禁止={}", ip);
            returnErrorPage(response, HttpStatus.FORBIDDEN.value(), formatRespDataWithCustomMsg(403, "非法访问，请1小时后重试", null), "非法访问，请1小时后重试");
            return false;
        }
        return true;
    }

    private boolean checkIpIsLock(String ip, RedisLockUtil redisLockUtil) {
        return redisLockUtil.hasKey(LOCK_IP_URL_KEY + ip);
    }

    private boolean addRequestTime(String ip, String uri, RedisLockUtil redisLockUtil) {
        String key = IP_URL_REQ_TIME + ip + uri;
        if (redisLockUtil.hasKey(key)) {
            long time = redisLockUtil.increment(key, 1L);
            if (time > LIMIT_TIMES) {
                redisLockUtil.getLock(LOCK_IP_URL_KEY + ip, ip, IP_LOCK_TIME);
                return false;
            }
        } else {
            redisLockUtil.getLock(key, (long) 1, 1);
        }
        return true;
    }

    private Boolean addIpRequestKeyLockAndCheckIsNotOutdated(String requestKey, RedisLockUtil redisLockUtil) {
        String key = IP_REQUEST_KEY + requestKey;
        if (redisLockUtil.hasKey(key)) {
            return false;
        } else {
            redisLockUtil.getLock(key, (long) 1, REQUEST_KEY_LOCK_TIME);
            return true;
        }
    }

    private void returnJson(HttpServletResponse response, Integer code, String json) {
        response.setStatus(code);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            log.error("[FirewallInterceptor] response error ---> {}", e.getMessage(), e);
        }
    }

    private void returnErrorPage(HttpServletResponse response, Integer code, String json, String notice) {
        InputStreamReader streamReader;
        String filePath = "templates/403/index.html";
        if (Objects.isNull(classLoader)) {
            returnJson(response, code, json);
            return;
        }
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            if (Objects.isNull(inputStream)) {
                returnJson(response, code, json);
                return;
            }
            streamReader = new InputStreamReader(inputStream);
            response.setStatus(code);
            response.setContentType("text/html; charset=utf-8");
            try (PrintWriter writer = response.getWriter()) {
                int readChar;
                while ((readChar = streamReader.read()) != -1) {
                    writer.write(readChar);
                }
            }
        } catch (Exception e) {
            log.error("[FirewallInterceptor] response error ---> {}", e.getMessage(), e);
            returnJson(response, code, json);
        }
    }

}
