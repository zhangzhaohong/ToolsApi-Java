package com.koala.service.redis.service;

public interface RedisService {
    String get(String key);
    String get(String key, String defaultValue);
    void set(String key, String value);
    void set(String key, String value, Long expire);
}
