package com.koala.tools.config;

import com.koala.tools.http.converter.CustomMessageConverter;
import com.koala.tools.http.processor.MixedHttpRequestProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/4 21:06
 * @description
 */
@Configuration
@Order
public class CoreWebConfig implements WebMvcConfigurer {

    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

    public CoreWebConfig() {
        CustomMessageConverter messageConverter = new CustomMessageConverter();
        messageConverters.add(messageConverter);
    }

    @Bean
    public MixedHttpRequestProcessor mixedHttpRequestProcessor() {
        return new MixedHttpRequestProcessor();
    }

    @Bean
    public RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor() {
        return new RequestResponseBodyMethodProcessor(messageConverters);
    }

    @Bean
    public ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor() {
        return new ServletModelAttributeMethodProcessor(true);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(mixedHttpRequestProcessor());
    }
}
