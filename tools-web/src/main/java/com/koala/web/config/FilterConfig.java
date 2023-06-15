package com.koala.web.config;

import com.koala.service.custom.filter.CustomCharacterEncodingFilter;
import com.koala.web.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/26 20:47
 * @description
 */
@Configuration
@EnableWebMvc
@DependsOn({"beanContext"})
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CustomCharacterEncodingFilter> registrationCharsetEncodingFilter() {
        FilterRegistrationBean<CustomCharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomCharacterEncodingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("customCharacterEncodingFilter");
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> registrationRequestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("requestLoggingFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
