package com.koala.tools.factory.builder;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 12:35
 * @description
 */
public class ConcreteDouYinApiBuilder extends DouYinApiBuilder {
    @Override
    public DouYinApiBuilder url(String url) {
        product.setUrl(url);
        return this;
    }

    @Override
    public DouYinApiBuilder host(String host) {
        product.setHost(host);
        return this;
    }

    @Override
    public DouYinApiBuilder getRedirectUrl() throws IOException, URISyntaxException {
        product.getRedirectUrl();
        return this;
    }

    @Override
    public DouYinApiBuilder getIdByUrl() {
        product.getIdByUrl();
        return this;
    }

    @Override
    public DouYinApiBuilder getItemIdByDirectUrl() {
        product.getItemIdByDirectUrl();
        return this;
    }

    @Override
    public DouYinApiBuilder getItemTypeByDirectUrl() {
        product.getItemTypeByDirectUrl();
        return this;
    }

    @Override
    public DouYinApiBuilder getItemInfo() throws IOException, URISyntaxException {
        product.getItemInfoData();
        return this;
    }

    @Override
    public void printParams() {
        product.printParams();
    }
}
