package com.koala.tools.config;

import com.koala.tools.http.processor.MixedHttpRequestProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/4 21:06
 * @description
 */
@Configuration
@DependsOn({"beanContext"})
public class CoreWebConfig implements WebMvcConfigurer {

    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(40960);
        //上传文件大小(单位为字节) 1024M     50*1024*1024
        resolver.setMaxUploadSize(1024 * 1024 * 1024L);
        return resolver;
    }

    @Bean
    public MixedHttpRequestProcessor mixedHttpRequestProcessor() {
        return new MixedHttpRequestProcessor();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(mixedHttpRequestProcessor());
    }

}
