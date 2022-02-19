package com.koala.tools.factory.builder;

import com.koala.tools.factory.product.DouYinApiProduct;

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

    public DouYinApiProduct getProduct() {
        return product;
    }
}
