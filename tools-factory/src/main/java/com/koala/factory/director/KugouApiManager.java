package com.koala.factory.director;

import com.koala.factory.builder.KugouApiBuilder;
import com.koala.factory.product.DouYinApiProduct;
import com.koala.factory.product.KugouApiProduct;
import com.koala.service.data.redis.service.RedisService;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:29
 * @description
 */
public class KugouApiManager {
    private final KugouApiBuilder builder;

    public KugouApiManager(KugouApiBuilder builder) {
        this.builder = builder;
    }

    public KugouApiProduct construct(RedisService redisService, String host, String url, String hash, String albumId, Integer version) throws IOException, URISyntaxException {
        builder.redis(redisService).host(host);
        if (StringUtils.hasLength(hash) && StringUtils.hasLength(albumId)) {
            builder.hashAndAlbumId(hash, albumId);
        } else {
            builder.url(url).prepareItemIdByShareUrl();
        }
        builder.version(version);
        return builder.getProduct();
    }
}
