package com.koala.tools.factory.director;

import com.koala.tools.factory.builder.DouYinApiBuilder;

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
}
