package com.koala.tools.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/5/29 11:56
 * @description
 */
@Component
@Slf4j
public class RedisLockUtil {

    private static final Long SUCCESS = 1L;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean getLock(String lockKey, Object value, int expireTime) {
        try {
            log.info("添加分布式锁key={},expireTime={}", lockKey, expireTime);
            String script = "if redis.call('setNx',KEYS[1],ARGV[1]) then if redis.call('get',KEYS[1])==ARGV[1] then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end end";
            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
            Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value, expireTime);
            if (SUCCESS.equals(result)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean releaseLock(String lockKey, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Object result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), value);
        return SUCCESS.equals(result);
    }

    public boolean hasKey(String lockKey) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(lockKey));
    }

    public Object getFromRedis(String key, Object defaultValue) {
        Object result = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(result)) {
            return defaultValue;
        }
        return result;
    }

    public Long increment(String key, Long value) {
        return redisTemplate.opsForValue().increment(key, value);
    }
}
