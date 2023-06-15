package com.koala.factory.director;

import com.koala.factory.builder.LanZouApiBuilder;
import com.koala.factory.product.LanZouApiProduct;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/15 16:05
 * @description
 */
public class LanZouApiManager {

    private final LanZouApiBuilder builder;

    public LanZouApiManager(LanZouApiBuilder builder) {
        this.builder = builder;
    }

    public LanZouApiProduct construct(String url, String password) throws IOException, URISyntaxException {
        /*
        * 初始化顺序
        * 1. url
        * 2. password
        * 3. getId
        * 4. getPageData
        * */
        builder.url(url).password(password).getIdByUrl().initPageData();
        builder.printParams();
        builder.printPageData();
        return builder.getProduct();
    }

}
