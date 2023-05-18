package com.koala.tools.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/17 22:33
 * @description 错误页面配置
 */
@Configuration
public class ErrorConfig implements ErrorPageRegistrar {
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        /*
          配置错误页面

          ErrorPage 有两个参数
          参数1 响应状态码  NOT_FOUND 404  INTERNAL_SERVER_ERROR 500
          参数2 出现响应状态码的时候的跳转路径  可以自定义跳转路径
         */
        ErrorPage error403 = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403?notice=请稍后重试");
        ErrorPage error404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage error500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");

        /*
          将ErrorPage 注册到注册器中
         */
        registry.addErrorPages(error403, error404, error500);

    }
}