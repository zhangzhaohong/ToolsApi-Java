package com.koala.factory.extra.tiktok;

import cn.hutool.json.JSONObject;
import com.koala.service.data.redis.service.RedisService;
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

import static com.koala.service.data.redis.RedisKeyPrefix.TIKTOK_COOKIE_DATA;
import static com.koala.service.data.redis.RedisKeyPrefix.TIKTOK_COOKIE_LOCK;

@Component
public class TiktokCookieUtil {

    @Resource
    private ResourceLoader resourceLoader;

    @Resource(name = "RedisService")
    private RedisService redisService;

    private static final Long TIKTOK_COOKIE_CACHE_TIME = 14 * 24 * 60 * 60L;

    private static final String BASE_URL_TIKTOK = "https://www.douyin.com";

    @Resource
    private RestTemplate restTemplate;

    public void doRefreshTiktokCookieTask() {
        refreshTiktokCookie(redisService.get(TIKTOK_COOKIE_DATA));
    }

    public String getTiktokCookie() {
        String lock = redisService.get(TIKTOK_COOKIE_LOCK);
        String cookie = redisService.get(TIKTOK_COOKIE_DATA);
        if (!StringUtils.hasLength(lock)) {
            return refreshTiktokCookie(getLocalTiktokCookie());
        } else {
            if (Objects.equals(lock, getCurrentDate())) {
                if (StringUtils.hasLength(cookie)) {
                    return cookie;
                } else {
                    return refreshTiktokCookie(null);
                }
            } else if (StringUtils.hasLength(cookie)) {
                return refreshTiktokCookie(cookie);
            } else {
                return refreshTiktokCookie(null);
            }
        }
    }

    private String getLocalTiktokCookie() {
        try {
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:cookie/custom.tiktok.cookie.txt");
            InputStream inputStream = resource.getInputStream();
            String custom = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            if (StringUtils.hasLength(custom)) {
                return custom;
            }
        } catch (Exception ignore) {
        }
        return "odin_tt=324fb4ea4a89c0c05827e18a1ed9cf9bf8a17f7705fcc793fec935b637867e2a5a9b8168c885554d029919117a18ba69; passport_csrf_token=f61602fc63757ae0e4fd9d6bdcee4810;";
    }

    private String refreshTiktokCookie(String cookie) {
        String cookieContent = Optional.ofNullable(cookie).orElse(getLocalTiktokCookie());
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
        ResponseEntity<String> responseEntity = RestTemplateUtil.post(new JSONObject(), BASE_URL_TIKTOK, cookies, restTemplate);
        List<String> cookieData = responseEntity.getHeaders().get("Set-Cookie");
        StringBuilder cookieString = new StringBuilder(cookieContent);
        if (Objects.isNull(cookieData)) {
            return cookieContent;
        }
        Objects.requireNonNull(cookieData).forEach(item -> {
            if (item.startsWith("ttwid")) {
                if (!(item.contains(";") && cookieContent.contains(item.split(";")[0])) && !cookieContent.contains(item)) {
                    cookieString.append(" ").append(item).append(";");
                }
            } else {
                cookieString.append(" ").append(item).append(";");
            }
        });
        redisService.set(TIKTOK_COOKIE_LOCK, getCurrentDate(), TIKTOK_COOKIE_CACHE_TIME);
        redisService.set(TIKTOK_COOKIE_DATA, cookieString.toString().trim(), TIKTOK_COOKIE_CACHE_TIME);
        return cookieString.toString().trim();
    }

    private static String getCurrentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        return formatter.format(currentTime);
    }

}
