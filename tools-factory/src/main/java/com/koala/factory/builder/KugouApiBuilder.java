package com.koala.factory.builder;

import com.koala.factory.extra.kugou.KugouCustomParamsUtil;
import com.koala.factory.product.DouYinApiProduct;
import com.koala.factory.product.KugouApiProduct;
import com.koala.service.data.redis.service.RedisService;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:10
 * @description
 */
public abstract class KugouApiBuilder {

    protected KugouApiProduct product = new KugouApiProduct();

    public abstract KugouApiBuilder url(String url);

    public abstract KugouApiBuilder hashAndAlbumId(String hash, String albumId);

    public abstract KugouApiBuilder host(String host);

    public abstract KugouApiBuilder customParams(KugouCustomParamsUtil customParams);

    public abstract KugouApiBuilder version(Integer version);

    public abstract KugouApiBuilder redis(RedisService redisService);

    public abstract KugouApiBuilder prepareItemIdByShareUrl() throws IOException, URISyntaxException;

    public abstract KugouApiBuilder getAlbumInfo() throws IOException, URISyntaxException;

    public abstract KugouApiBuilder getAlbumMusicInfo() throws IOException, URISyntaxException;

    public KugouApiProduct getProduct() {
        return product;
    }
}
