package com.koala.tools.factory.builder;

import com.koala.tools.factory.product.LanZouApiProduct;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/15 16:09
 * @description
 */
public abstract class LanZouApiBuilder {
    /**
     * 创建产品对象
     */
    protected LanZouApiProduct product = new LanZouApiProduct();

    /**
     * @param url 分享文件入参
     * @return Builder
     */
    public abstract LanZouApiBuilder url(String url);

    /**
     * @param password 密码
     * @return Builder
     */
    public abstract LanZouApiBuilder password(String password);

    /**
     * @return Builder
     * 仅用来初始化页面数据
     */
    public abstract LanZouApiBuilder initPageData() throws IOException, URISyntaxException;

    /**
     * @return Builder
     */
    public abstract LanZouApiBuilder getIdByUrl();

    /**
     * 打印初始化入参
     */
    public abstract void printParams();

    /**
     * 打印pageData到输出
     */
    public abstract void printPageData();

    public LanZouApiProduct getProduct() {
        return product;
    }
}
