package com.koala.tools.factory.builder;

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
}
