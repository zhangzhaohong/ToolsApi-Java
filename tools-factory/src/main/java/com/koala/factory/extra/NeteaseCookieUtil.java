package com.koala.factory.extra;

import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

    public String getNeteaseCookie() {
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
}
