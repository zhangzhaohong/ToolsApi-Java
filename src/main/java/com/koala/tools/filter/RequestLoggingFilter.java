package com.koala.tools.filter;

import com.koala.tools.http.wrapper.CustomHttpServletRequestWrapper;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 12:50
 * @description
 */
@Slf4j
public class RequestLoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CustomHttpServletRequestWrapper request = new CustomHttpServletRequestWrapper((HttpServletRequest) servletRequest);
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
            parameterMaps.put(name, request.getParameter(name));
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
        // 获取body
        try {
            String body = new String(request.getBody(), "UTF-8");
            if (!StringUtils.isEmpty(body)) {
                if (JsonUtils.isJson(body)) {
                    map.put("Body", GsonUtil.toMaps(body));
                } else {
                    Map<String, Object> params = new HashMap<>();
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
        filterChain.doFilter(request, response);
        log.info("[ResponseInfo]" + response.getContentType());
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
