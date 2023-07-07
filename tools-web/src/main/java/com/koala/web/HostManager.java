package com.koala.web;

import com.koala.service.data.redis.service.RedisService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static com.koala.service.data.redis.RedisKeyPrefix.SERVICE_HOST;

/**
 * @author koala
 * @version 1.0
 * @date 2023/7/7 18:55
 * @description
 */
@Component
@DependsOn({"beanContext", "getHost", "RedisService"})
public class HostManager {

    @Resource(name = "getHost")
    private String host;

    @Resource
    private RedisService redisService;

    public String getHost() {
        String cachedHost = redisService.getAndPersist(SERVICE_HOST);
        if (StringUtils.hasLength(cachedHost)) {
            return cachedHost;
        }
        return host;
    }
}
