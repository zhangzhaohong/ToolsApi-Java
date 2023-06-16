package com.koala.factory.builder;

import com.koala.factory.product.NeteaseApiProduct;

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

    public abstract NeteaseApiBuilder getIdByUrl();

    public abstract NeteaseApiBuilder initRequest() throws Exception;

    public abstract NeteaseApiBuilder getItemInfoData() throws Exception;

    public NeteaseApiProduct getProduct() {
        return product;
    }
}
