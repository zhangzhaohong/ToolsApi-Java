package com.koala.tools.filter;


import com.koala.tools.http.wrapper.CustomHttpServletRequestWrapper;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/26 20:43
 * @description
 */
@Slf4j
public class CharacterEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("init CharacterEncodingFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CustomHttpServletRequestWrapper requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            requestWrapper = new CustomHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }
        if (Objects.isNull(requestWrapper)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        requestWrapper.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        filterChain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
