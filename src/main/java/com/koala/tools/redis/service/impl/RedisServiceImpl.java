package com.koala.tools.redis.service.impl;

import com.koala.tools.redis.service.RedisService;
import com.koala.tools.redis.utils.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
@Service("RedisService")
public class RedisServiceImpl implements RedisService {

    private final static Long defaultExpireTime = 7 * 24 * 60 * 60L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public String get(String key) {
        return get(key, null);
    }

    @Override
    public String get(String key, String defaultValue) {
        try {
            byte[] result = redisTemplate.execute((RedisConnection redisConnection) -> redisConnection.get(key.getBytes(StandardCharsets.UTF_8)));
            if (Objects.isNull(result)) {
                return defaultValue;
            }
            return (String) SerializeUtil.unserialize(result);
        } catch (Exception e) {
            log.error("getRedisDataError", e);
        }
        return null;
    }

    @Override
    public void set(String key, String value) {
        set(key, value, defaultExpireTime);
    }

    @Override
    public void set(String key, String value, Long expireTime) {
        redisTemplate.execute((RedisConnection redisConnection) -> redisConnection.set(key.getBytes(StandardCharsets.UTF_8), Objects.requireNonNull(SerializeUtil.serialize(value))));
    }
}