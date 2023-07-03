package com.koala.service.utils;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@CacheConfig(cacheNames = "cookies")
public class CookieUtil {

    /**
     * 获取request中的Cookie对象Map
     */
    @Cacheable(key = "#request.getSession().getId()")
    public Map<String, String> getCookies(HttpServletRequest request) {

        Map<String, String> cookieMap;

        Cookie[] cookies = request.getCookies();
        cookieMap = new HashMap<>();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie.getValue());
            }
        }
        /* 特殊处理__csrf */
        cookieMap.putIfAbsent("__csrf", RandomUtil.randomStringUpper(10));
        return cookieMap;
    }

    /**
     * 写入Cookie到response
     */
    public static void setCookie(HttpHeaders httpHeaders, HttpServletResponse response) {
        List<String> setCookie = httpHeaders.get("SET-COOKIE");

        if (setCookie != null) {
            for (String string : setCookie) {
                List<String> entry = CharSequenceUtil.split(string, ";");
                if (entry.size() > 0) {
                    List<String> cookieItem = CharSequenceUtil.split(entry.get(0).trim(), "=");
                    if (cookieItem.size() == 2) {
                        if (StringUtils.hasLength(cookieItem.get(1).trim())) {
                            Cookie cookie = new Cookie(cookieItem.get(0).trim(), cookieItem.get(1).trim());
                            cookie.setPath("/");
                            cookie.setMaxAge(14 * 24 * 60 * 60);
                            response.addCookie(cookie);
                        }
                    }
                }

            }
        }
    }


}