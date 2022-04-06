package com.koala.tools.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.koala.tools.http.converter.CustomMessageConverter;
import com.koala.tools.http.processor.MixedHttpRequestProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/4 21:06
 * @description
 */
@Configuration
@EnableWebMvc
@DependsOn({"beanContext"})
public class CoreWebConfig implements WebMvcConfigurer {

    @Bean(name = "customRequestMappingHandlerAdapter")
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> converters = adapter.getMessageConverters();
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.QuoteFieldNames,
                //保留空的字段
                SerializerFeature.WriteMapNullValue,
                //List null-> []
                SerializerFeature.WriteNullListAsEmpty,
                // 日期格式化
                SerializerFeature.WriteDateUseDateFormat,
                //String null -> ""
                SerializerFeature.WriteNullStringAsEmpty);
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.TEXT_HTML);
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        mediaTypeList.add(MediaType.APPLICATION_FORM_URLENCODED);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypeList);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        CustomMessageConverter customMessageConverter = new CustomMessageConverter();
        converters.add(fastJsonHttpMessageConverter);
        converters.add(customMessageConverter);
        adapter.setMessageConverters(converters);
        return adapter;
    }

    @Bean(name = "customMultipartResolver")
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

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

}
