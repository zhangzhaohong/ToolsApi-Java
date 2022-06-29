package com.koala.tools.filter;

import com.koala.tools.BeanContext;
import com.koala.tools.http.wrapper.CustomHttpServletRequestWrapper;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.*;
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
@DependsOn(value = {"beanContext", "customMultipartResolver"})
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
        Object requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest) {
            if (!Objects.isNull(servletRequest.getContentType()) && servletRequest.getContentType().contains(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
                requestWrapper = servletRequest;
            } else {
                requestWrapper = new CustomHttpServletRequestWrapper((HttpServletRequest) servletRequest);
            }
        }
        if (Objects.isNull(requestWrapper)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Map<String, Object> map = new HashMap<>(0);
        // 写入info
        HttpServletRequest wrapper = (HttpServletRequest) requestWrapper;
        // Get request URL.
        map.put("Url", wrapper.getRequestURL());
        map.put("Method", wrapper.getMethod());
        map.put("Protocol", wrapper.getProtocol());
        // 获取header信息
        List<Map<String, String>> headerList = new ArrayList<>();
        Map<String, String> headerMaps = new HashMap<>();
        for (Enumeration<String> enu = wrapper.getHeaderNames(); enu.hasMoreElements(); ) {
            String name = enu.nextElement();
            headerMaps.put(name, wrapper.getHeader(name));
        }
        headerList.add(headerMaps);
        map.put("Headers", headerList);

        //获取parameters信息
        List<Map<String, String>> parameterList = new ArrayList<>();
        Map<String, String> parameterMaps = new HashMap<>(0);
        for (Enumeration<String> names = wrapper.getParameterNames(); names.hasMoreElements(); ) {
            String name = names.nextElement();
            parameterMaps.put(name, wrapper.getParameter(name));
        }
        parameterList.add(parameterMaps);
        map.put("Parameters", parameterList);
        String line = "";
        int idx = wrapper.getRequestURL().indexOf("?");
        if (idx != -1) {
            line = wrapper.getRequestURL().substring(idx + 1);
        } else {
            line = null;
        }
        if (line != null) {
            map.put("Context", new String[]{line});
        }
        if (requestWrapper instanceof CustomHttpServletRequestWrapper) {
            CustomHttpServletRequestWrapper customWrapper = (CustomHttpServletRequestWrapper) requestWrapper;
            // 获取body
            try {
                String body = customWrapper.getBody();
                if (!StringUtils.isEmpty(body)) {
                    if (JsonUtils.isJson(body)) {
                        map.put("Body", GsonUtil.toMaps(body));
                    } else {
                        Map<String, Object> params = new HashMap<>(0);
                        String[] tmp = body.split("&");
                        Arrays.stream(tmp).forEach(item -> {
                            String[] param = item.split("=");
                            if (param.length > 1) {
                                params.put(param[0], param[1]);
                            }
                        });
                        map.put("Body", params);
                    }
                }
            } catch (Exception e) {
                log.error("[GetRequestInfoError]", e);
            }
        }
        log.info("[RequestInfo]" + GsonUtil.toString(map));
        if (requestWrapper instanceof CustomHttpServletRequestWrapper) {
            filterChain.doFilter(getRequest((CustomHttpServletRequestWrapper) requestWrapper), response);
        } else {
            filterChain.doFilter(getRequest((HttpServletRequest) requestWrapper), response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private ServletRequest getRequest(ServletRequest request) {
        String contentType = request.getContentType();
        if (!StringUtils.isEmpty(contentType) && contentType.contains("multipart") && request instanceof HttpServletRequest) {
            return multipartResolver.resolveMultipart((HttpServletRequest) request);
        } else {
            return request;
        }
    }
}
