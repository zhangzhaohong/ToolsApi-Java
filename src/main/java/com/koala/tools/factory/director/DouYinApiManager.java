package com.koala.tools.factory.director;

import com.koala.tools.factory.builder.DouYinApiBuilder;
import com.koala.tools.factory.product.DouYinApiProduct;
import com.koala.tools.redis.service.RedisService;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 12:44
 * @description
 */
public class DouYinApiManager {
    private final DouYinApiBuilder builder;

    public DouYinApiManager(DouYinApiBuilder builder) {
        this.builder = builder;
    }

    public DouYinApiProduct construct(RedisService redisService, String host, String url, Integer version) throws IOException, URISyntaxException {
        builder.redis(redisService).host(host).url(url).getIdByUrl().getRedirectUrl().getItemTypeByDirectUrl().getItemIdByDirectUrl().getItemInfo();
        builder.version(version);
        builder.printParams();
        return builder.getProduct();
    }
}
