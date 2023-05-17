package com.koala.tools.interceptor;

import com.koala.tools.redis.RedisLockUtil;
import com.koala.tools.utils.RemoteIpUtils;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import static com.koala.tools.utils.RespUtil.formatRespDataWithCustomMsg;

/**
 * @author koala
 * @version 1.0
 * @date 2022/5/29 11:49
 * @description
 */
@Slf4j
@Component
public class FirewallInterceptor implements HandlerInterceptor {

    private static final String LOCK_IP_URL_KEY = "lock_ip_";

    private static final String IP_URL_REQ_TIME = "ip_url_times_";

    private static final Integer LIMIT_TIMES = 5;

    private static final Integer IP_LOCK_TIME = 60 * 60;

    private static final String[] WHITE_LIST_HOST = new String[]{"127.0.0.1", "0:0:0:0:0:0:0:1", "192.168.2.250"};

    private static final String[] WHITE_LIST_PATH = new String[]{"/assets/", "/actuator", "/favicon.ico"};

    @Resource
    private RedisLockUtil redisLockUtil;

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        final String ip = RemoteIpUtils.getRemoteIpByServletRequest(request, true);
        log.info("request请求地址uri={},ip={}", request.getRequestURI(), ip);
        for (String path : WHITE_LIST_PATH) {
            if (request.getRequestURI().startsWith(path)) {
                log.info("白名单请求，自动放过={}", ip);
                return true;
            }
        }
        if (Arrays.asList(WHITE_LIST_HOST).contains(ip)) {
            log.info("白名单IP，自动放过={}", ip);
            return true;
        }
        if (!redisLockUtil.getRedisStatus()) {
            log.info("redis连接异常，自动放过={}", ip);
            return true;
        }
        if (checkIpIsLock(ip, redisLockUtil)) {
            log.info("ip访问被禁止={}", ip);
            returnJson(response, 403, formatRespDataWithCustomMsg(403, "非法访问，请1小时后重试", null));
            return false;
        }
        if (!addRequestTime(ip, request.getRequestURI(), redisLockUtil)) {
            log.info("ip访问被禁止={}", ip);
            returnJson(response, 403, formatRespDataWithCustomMsg(403, "非法访问，请1小时后重试", null));
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

    private void returnJson(HttpServletResponse response, Integer code, String json) {
        response.setStatus(code);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/json; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            log.error("FirewallInterceptor response error ---> {}", e.getMessage(), e);
        }
    }

}
