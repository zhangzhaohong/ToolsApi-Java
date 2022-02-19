package com.koala.tools.factory.director;

import com.koala.tools.factory.builder.DouYinApiBuilder;
import com.koala.tools.factory.product.DouYinApiProduct;

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

    public DouYinApiProduct construct(String url) throws IOException, URISyntaxException {
        builder.url(url).getIdByUrl().getRedirectUrl().getItemIdByDirectUrl();
        builder.printParams();
        return builder.getProduct();
    }
}
