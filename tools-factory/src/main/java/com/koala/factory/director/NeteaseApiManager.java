package com.koala.factory.director;

import com.koala.factory.builder.NeteaseApiBuilder;
import com.koala.factory.product.NeteaseApiProduct;
import com.koala.service.data.redis.service.RedisService;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 14:06
 * @description
 */
public class NeteaseApiManager {
    private final NeteaseApiBuilder builder;

    public NeteaseApiManager(NeteaseApiBuilder builder) {
        this.builder = builder;
    }

    public NeteaseApiProduct construct(RedisService redisService, String host, String url, Integer version) throws Exception {
        builder.redis(redisService).host(host).url(url).level(null).getIdByUrl().initRequest().getItemInfoData().getItemDetailData();
        builder.version(version);
        return builder.getProduct();
    }
}
