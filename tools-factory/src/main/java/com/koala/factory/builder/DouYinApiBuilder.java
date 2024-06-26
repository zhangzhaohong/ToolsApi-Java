package com.koala.factory.builder;

import com.koala.factory.product.DouYinApiProduct;
import com.koala.service.data.redis.service.RedisService;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 12:34
 * @description
 */
public abstract class DouYinApiBuilder {
    protected DouYinApiProduct product = new DouYinApiProduct();

    /**
     * @param url 地址
     * @return
     */
    public abstract DouYinApiBuilder url(String url);

    public abstract DouYinApiBuilder host(String host);

    public abstract DouYinApiBuilder getRedirectUrl() throws IOException, URISyntaxException;

    /**
     * @return Builder
     */
    public abstract DouYinApiBuilder getIdByUrl();

    /**
     * @return Builder
     */
    public abstract DouYinApiBuilder getItemIdByDirectUrl() throws IOException, URISyntaxException;

    /**
     * @return Builder
     */
    public abstract DouYinApiBuilder getItemTypeByDirectUrl();

    /**
     * @return Builder
     */
    public abstract DouYinApiBuilder getItemInfo() throws IOException, URISyntaxException;

    public abstract DouYinApiBuilder version(Integer version);

    /**
     * 打印初始化入参
     */
    public abstract void printParams();

    public DouYinApiProduct getProduct() {
        return product;
    }

    public abstract DouYinApiBuilder redis(RedisService redisService);

    public abstract DouYinApiBuilder isMobile(Boolean isMobile);

    public abstract DouYinApiBuilder cookie(String cookie);
}
