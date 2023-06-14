package com.koala.service.data.redis.service;

public interface RedisService {
    String get(String key);
    String get(String key, String defaultValue);
    void set(String key, String value);
    void set(String key, String value, Long expire);
}
