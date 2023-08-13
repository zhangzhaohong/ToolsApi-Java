package com.koala.factory.extra.netease;

import cn.hutool.json.JSONObject;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.RestTemplateUtil;
import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.koala.service.data.redis.RedisKeyPrefix.*;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 20:45
 * @description
 */
@Component
public class NeteaseCookieUtil {

    @Resource
    private ResourceLoader resourceLoader;

    @Resource(name = "RedisService")
    private RedisService redisService;

    private static final Long NETEASE_COOKIE_CACHE_TIME = 14 * 24 * 60 * 60L;

    @Resource(name = "getHost")
    private String host;

    @Resource
    private RestTemplate restTemplate;

    public void doRefreshNeteaseCookieTask() {
        refreshNeteaseCookie(null);
    }

    public String getNeteaseCookie() {
        String lock = redisService.get(NETEASE_COOKIE_LOCK);
        String cookie = redisService.get(NETEASE_COOKIE_DATA);
        if (!StringUtils.hasLength(lock)) {
            return refreshNeteaseCookie(getLocalNeteaseCookie());
        } else {
            if (Objects.equals(lock, getCurrentDate())) {
                if (StringUtils.hasLength(cookie)) {
                    return cookie;
                } else {
                    return refreshNeteaseCookie(null);
                }
            } else if (StringUtils.hasLength(cookie)) {
                return refreshNeteaseCookie(cookie);
            } else {
                return refreshNeteaseCookie(null);
            }
        }
    }

    private String getLocalNeteaseCookie() {
        try {
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:cookie/custom.netease.cookie.txt");
            InputStream inputStream = resource.getInputStream();
            String custom = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.hasLength(custom)) {
                return custom;
            }
        } catch (Exception ignore) {
        }
        return "MUSIC_U=1eb9ce22024bb666e99b6743b2222f29ef64a9e88fda0fd5754714b900a5d70d993166e004087dd3b95085f6a85b059f5e9aba41e3f2646e3cebdbec0317df58c119e5;appver=8.9.75;";
    }

    private String refreshNeteaseCookie(String cookie) {
        String cookieContent = Optional.ofNullable(cookie).orElse(getLocalNeteaseCookie());
        Map<String, String> cookies = new HashMap<>();
        Arrays.stream(cookieContent.split(";")).forEach(item -> {
            try {
                String[] cookieItem = item.split("=");
                if (cookieItem.length == 2) {
                    cookies.put(cookieItem[0], cookieItem[1]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ResponseEntity<String> responseEntity = RestTemplateUtil.post(new JSONObject(), getCurrentHost() + "tools/Netease/weapi/login/token/refresh", cookies, restTemplate);
        if (StringUtils.hasLength(responseEntity.getBody())) {
            Map<String, Object> data = GsonUtil.toMaps(responseEntity.getBody());
            if ((Double) data.get("code") == 200) {
                List<String> cookieData = responseEntity.getHeaders().get("Set-Cookie");
                StringBuilder cookieString = new StringBuilder();
                for (String item : Objects.requireNonNull(cookieData)) {
                    cookieString.append(" ").append(item).append(";");
                }
                if (!cookieContent.contains("__remember_me")) {
                    cookieString.append(" __remember_me=true;");
                }
                if (!cookieContent.contains("appver")) {
                    cookieString.append(" appver=8.9.75;");
                }
                redisService.set(NETEASE_COOKIE_LOCK, getCurrentDate(), NETEASE_COOKIE_CACHE_TIME);
                redisService.set(NETEASE_COOKIE_DATA, cookieString.toString().trim(), NETEASE_COOKIE_CACHE_TIME);
                return cookieString.toString().trim();
            }
        }
        return null;
    }

    public static String getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(currentTime);
    }

    public String getCurrentHost() {
        String cachedHost = redisService.getAndPersist(SERVICE_HOST);
        if (StringUtils.hasLength(cachedHost)) {
            return cachedHost;
        }
        return host;
    }

}
