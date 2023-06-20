package com.koala.factory.builder;

import com.koala.factory.extra.kugou.KugouCustomParamsUtil;
import com.koala.service.data.redis.service.RedisService;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:14
 * @description
 */
public class ConcreteKugouApiBuilder extends KugouApiBuilder {
    @Override
    public KugouApiBuilder url(String url) {
        product.setUrl(url);
        return this;
    }

    @Override
    public KugouApiBuilder hashAndAlbumId(String hash, String albumId) {
        product.setHashAndAlbumId(hash, albumId);
        return this;
    }

    @Override
    public KugouApiBuilder host(String host) {
        product.setHost(host);
        return this;
    }

    @Override
    public KugouApiBuilder customParams(KugouCustomParamsUtil customParams) {
        product.setCustomParams(customParams.getKugouCustomParams());
        return this;
    }

    @Override
    public KugouApiBuilder version(Integer version) {
        product.setVersion(version);
        return this;
    }

    @Override
    public KugouApiBuilder redis(RedisService redisService) {
        product.setRedis(redisService);
        return this;
    }

    @Override
    public KugouApiBuilder prepareItemIdByShareUrl() throws IOException, URISyntaxException {
        product.prepareItemIdByShareUrl();
        return this;
    }

    @Override
    public KugouApiBuilder getAlbumInfo() throws IOException, URISyntaxException {
        product.getAlbumInfo();
        return this;
    }

    @Override
    public KugouApiBuilder getAlbumMusicInfo() throws IOException, URISyntaxException {
        product.getAlbumMusicInfo();
        return this;
    }
}
