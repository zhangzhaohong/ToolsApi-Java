package com.koala.factory.builder;

import com.koala.factory.product.NeteaseApiProduct;
import com.koala.service.data.redis.service.RedisService;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 13:52
 * @description
 */
public abstract class NeteaseApiBuilder {

    protected NeteaseApiProduct product = new NeteaseApiProduct();

    public abstract NeteaseApiBuilder url(String url);

    public abstract NeteaseApiBuilder level(String level);

    public abstract NeteaseApiBuilder host(String host);

    public abstract NeteaseApiBuilder cookie(String cookie);

    public abstract NeteaseApiBuilder version(Integer version);

    public abstract NeteaseApiBuilder redis(RedisService redisService);

    public abstract NeteaseApiBuilder getIdByUrl();

    public abstract NeteaseApiBuilder initRequest() throws Exception;

    public abstract NeteaseApiBuilder getItemInfoData() throws Exception;

    public abstract NeteaseApiBuilder getItemDetailData() throws Exception;

    public abstract NeteaseApiBuilder getItemLyricData() throws Exception;

    public NeteaseApiProduct getProduct() {
        return product;
    }
}
