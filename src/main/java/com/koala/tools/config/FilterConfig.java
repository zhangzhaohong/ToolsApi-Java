package com.koala.tools.config;

import com.koala.tools.filter.CharacterEncodingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/26 20:47
 * @description
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> registrationCharsetEncodingFilter() {
        FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CharacterEncodingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("CharacterEncodingFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
