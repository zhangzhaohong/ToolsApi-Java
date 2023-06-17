package com.koala.factory.builder;

import com.koala.service.data.redis.service.RedisService;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 13:56
 * @description
 */
public class ConcreteNeteaseApiBuilder extends NeteaseApiBuilder {
    @Override
    public NeteaseApiBuilder url(String url) {
        product.setUrl(url);
        return this;
    }

    @Override
    public NeteaseApiBuilder level(String level) {
        product.setLevel(level);
        return this;
    }

    @Override
    public NeteaseApiBuilder host(String host) {
        product.setHost(host);
        return this;
    }

    @Override
    public NeteaseApiBuilder cookie(String cookie) {
        product.setCookie(cookie);
        return this;
    }

    @Override
    public NeteaseApiBuilder version(Integer version) {
        product.setVersion(version);
        return this;
    }

    @Override
    public NeteaseApiBuilder redis(RedisService redisService) {
        product.setRedis(redisService);
        return this;
    }

    @Override
    public NeteaseApiBuilder getIdByUrl() {
        product.getIdByUrl();
        return this;
    }

    @Override
    public NeteaseApiBuilder initRequest() throws Exception {
        product.initRequest();
        return this;
    }

    @Override
    public NeteaseApiBuilder getItemInfoData() throws Exception {
        product.getItemInfoData();
        return this;
    }

    @Override
    public NeteaseApiBuilder getItemDetailData() throws Exception {
        product.getItemDetailData();
        return this;
    }
}
