package com.koala.tools.config;

import com.koala.tools.filter.CharacterEncodingFilter;
import com.koala.tools.filter.RequestLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/26 20:47
 * @description
 */
@Configuration
@EnableWebMvc
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> registrationCharsetEncodingFilter() {
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CharacterEncodingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("characterEncodingFilter");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> registrationRequestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("requestLoggingFilter");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
        return registrationBean;
    }
}
