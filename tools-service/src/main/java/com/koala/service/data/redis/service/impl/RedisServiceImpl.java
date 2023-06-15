package com.koala.service.data.redis.service.impl;

import com.koala.service.data.redis.service.RedisService;
import com.koala.service.data.redis.utils.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service("RedisService")
public class RedisServiceImpl implements RedisService {

    private final static Long DEFAULT_EXPIRE_TIME = 7 * 24 * 60 * 60L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String get(String key) {
        return get(key, null);
    }

    @Override
    public String get(String key, String defaultValue) {
        Object result = redisTemplate.opsForValue().get(key);
        if (!Objects.isNull(result)) {
            return String.valueOf(result);
        }
        return defaultValue;
    }

    @Override
    public void set(String key, String value) {
        set(key, value, DEFAULT_EXPIRE_TIME);
    }

    @Override
    public void set(String key, String value, Long expireTime) {
        redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
    }
}
