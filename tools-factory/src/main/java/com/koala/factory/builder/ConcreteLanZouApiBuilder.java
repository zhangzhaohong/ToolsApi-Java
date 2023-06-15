package com.koala.factory.builder;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/15 16:28
 * @description
 */
public class ConcreteLanZouApiBuilder extends LanZouApiBuilder{
    @Override
    public LanZouApiBuilder url(String url) {
        product.setUrl(url);
        return this;
    }

    @Override
    public LanZouApiBuilder password(String password) {
        product.setPassword(password);
        return this;
    }

    @Override
    public LanZouApiBuilder initPageData() throws IOException, URISyntaxException {
        product.initPageData();
        return this;
    }

    @Override
    public LanZouApiBuilder getIdByUrl() {
        product.getIdByUrl();
        return this;
    }

    @Override
    public void printParams() {
        product.printParams();
    }

    @Override
    public void printPageData() {
        product.printPageData();
    }
}
