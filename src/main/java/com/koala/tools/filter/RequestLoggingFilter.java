package com.koala.tools.filter;

import com.koala.tools.BeanContext;
import com.koala.tools.http.wrapper.CustomHttpServletRequestWrapper;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 12:50
 * @description
 */
@Slf4j
@DependsOn(value = {"beanContext"})
@WebFilter(urlPatterns = "/*", filterName = "requestLoggingFilter")
public class RequestLoggingFilter implements Filter {

    private MultipartResolver multipartResolver = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        log.info("init RequestLoggingFilter");
        multipartResolver = (MultipartResolver) BeanContext.getBean("customMultipartResolver");
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
        Map<String, Object> map = new HashMap<>(0);

        // Get request URL.
        map.put("Url", requestWrapper.getRequestURL());
        map.put("Method", requestWrapper.getMethod());
        map.put("Protocol", requestWrapper.getProtocol());

        // 获取header信息
        List<Map<String, String>> headerList = new ArrayList<>();
        Map<String, String> headerMaps = new HashMap<>();
        for (Enumeration<String> enu = requestWrapper.getHeaderNames(); enu.hasMoreElements(); ) {
            String name = enu.nextElement();
            headerMaps.put(name, requestWrapper.getHeader(name));
        }
        headerList.add(headerMaps);
        map.put("Headers", headerList);

        //获取parameters信息
        List<Map<String, String>> parameterList = new ArrayList<>();
        Map<String, String> parameterMaps = new HashMap<>(0);
        for (Enumeration<String> names = requestWrapper.getParameterNames(); names.hasMoreElements(); ) {
            String name = names.nextElement();
            parameterMaps.put(name, requestWrapper.getParameter(name));
        }
        parameterList.add(parameterMaps);
        map.put("Parameters", parameterList);
        String line = "";
        int idx = requestWrapper.getRequestURL().indexOf("?");
        if (idx != -1) {
            line = requestWrapper.getRequestURL().substring(idx + 1);
        } else {
            line = null;
        }
        if (line != null) {
            map.put("Context", new String[]{line});
        }

        // 获取body
        try {
            String body = requestWrapper.getBody();
            if (!StringUtils.isEmpty(body)) {
                if (JsonUtils.isJson(body)) {
                    map.put("Body", GsonUtil.toMaps(body));
                } else {
                    Map<String, Object> params = new HashMap<>(0);
                    String[] tmp = body.split("&");
                    Arrays.stream(tmp).forEach(item -> {
                        String[] param = item.split("=");
                        params.put(param[0], param[1]);
                    });
                    map.put("Body", params);
                }
            }
        } catch (Exception e) {
            log.error("[GetRequestInfoError]", e);
        }

        log.info("[RequestInfo]" + GsonUtil.toString(map));
        filterChain.doFilter(getRequest(requestWrapper), response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private ServletRequest getRequest(ServletRequest request) {
        String contentType = request.getContentType();
        if (!StringUtils.isEmpty(contentType) && contentType.contains("multipart")) {
            return multipartResolver.resolveMultipart((HttpServletRequest) request);
        } else {
            return request;
        }
    }
}
