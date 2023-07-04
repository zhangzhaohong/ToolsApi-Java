package com.koala.factory.extra.netease;

import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.CookieUtil;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.service.utils.RandomUserAgentGenerator;
import jakarta.annotation.Resource;
import org.apache.hc.client5.http.cookie.Cookie;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.koala.service.data.redis.RedisKeyPrefix.NETEASE_COOKIE_DATA;
import static com.koala.service.data.redis.RedisKeyPrefix.NETEASE_COOKIE_LOCK;
import static com.koala.service.utils.HeaderUtil.getNeteaseHttpHeader;

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

    public String getNeteaseCookie() throws IOException {
        String lock = redisService.get(NETEASE_COOKIE_LOCK);
        String cookie = redisService.get(NETEASE_COOKIE_DATA);
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

    private String refreshNeteaseCookie(String cookie) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", Optional.ofNullable(cookie).orElse(getLocalNeteaseCookie()));
        headers.put("User-Agent", RandomUserAgentGenerator.getNeteaseUserAgent("pc"));
        String response = HttpClientUtil.doPost(host + "tools/Netease/login/token/refresh/generator", headers, null);
        if (StringUtils.hasLength(response)) {
            Map<String, Object> data = GsonUtil.toMaps(response);
            if ((Double) data.get("code") == 200) {
                String cookieData = String.valueOf(data.get("cookie"));
                if (cookieData.equals("null") || cookieData.equals("")) {
                    return null;
                }
                redisService.set(NETEASE_COOKIE_LOCK, getCurrentDate(), NETEASE_COOKIE_CACHE_TIME);
                redisService.set(NETEASE_COOKIE_DATA, cookieData, NETEASE_COOKIE_CACHE_TIME);
                return cookieData;
            }
        }
        return null;
    }

    public static String getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(currentTime);
    }

}
