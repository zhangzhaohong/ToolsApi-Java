package com.koala.tools.filter;

import com.koala.tools.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 12:50
 * @description
 */
@Slf4j
@WebFilter(urlPatterns = "/*",filterName = "RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Map<String, Object> map = new HashMap<>(0);

        // Get request URL.
        map.put("Url", request.getRequestURL());
        map.put("Method", request.getMethod());
        map.put("Protocol", request.getProtocol());

        // 获取header信息
        List<Map<String, String>> headerList = new ArrayList<>();
        Map<String, String> headerMaps = new HashMap<>();
        for (Enumeration<String> enu = request.getHeaderNames(); enu.hasMoreElements(); ) {
            String name = enu.nextElement();
            headerMaps.put(name, request.getHeader(name));
        }
        headerList.add(headerMaps);
        map.put("Headers", headerList);

        //获取parameters信息
        List<Map<String, String>> parameterList = new ArrayList<>();
        Map<String, String> parameterMaps = new HashMap<>(0);
        for (Enumeration<String> names = request.getParameterNames(); names.hasMoreElements(); ) {
            String name = names.nextElement();
            parameterMaps.put(name, new String(request.getParameter(name).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
        }
        parameterList.add(parameterMaps);
        map.put("Parameters", parameterList);
        String line = "";
        int idx = request.getRequestURL().indexOf("?");
        if (idx != -1) {
            line = request.getRequestURL().substring(idx + 1);
        } else {
            line = null;
        }
        if (line != null) {
            map.put("Context", new String[]{line});
        }
        log.info("[RequestInfo]" + GsonUtil.toString(map));
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
