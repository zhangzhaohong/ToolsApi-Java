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

    public NeteaseApiProduct construct(RedisService redisService, String host, String cookie, String url, String level, Boolean lyric, Integer version) throws Exception {
        builder.redis(redisService).host(host).cookie(cookie).url(url).level(level).getIdByUrl().initRequest().getItemInfoData().getItemDetailData();
        if (lyric) {
            builder.getItemLyricData();
        }
        builder.version(version);
        return builder.getProduct();
    }

    public NeteaseApiProduct construct(RedisService redisService, String host, String cookie, String url, Integer version) throws Exception {
        builder.redis(redisService).host(host).cookie(cookie).url(url).getIdByUrl().getItemLyricData();
        builder.version(version);
        return builder.getProduct();
    }
}
