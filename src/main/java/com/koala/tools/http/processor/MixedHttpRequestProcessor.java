package com.koala.tools.http.processor;

import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.http.converter.CustomMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 11:22
 * @description
 */
public class MixedHttpRequestProcessor implements HandlerMethodArgumentResolver {

    private final RequestResponseBodyMethodProcessor jsonResolver;
    private final ServletModelAttributeMethodProcessor formResolver;

    public MixedHttpRequestProcessor() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        CustomMessageConverter messageConverter = new CustomMessageConverter();
        messageConverters.add(messageConverter);

        jsonResolver = new RequestResponseBodyMethodProcessor(messageConverters);
        formResolver = new ServletModelAttributeMethodProcessor(false);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        MixedHttpRequest mixedHttpRequest = methodParameter.getMethodAnnotation(MixedHttpRequest.class);
        return (!Objects.isNull(mixedHttpRequest));
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletRequest servletRequest = nativeWebRequest.getNativeRequest(ServletRequest.class);
        if (Objects.isNull(servletRequest))
            throw new IllegalArgumentException("servletRequest不能为null");
        String contentType = servletRequest.getContentType();
        if (contentType == null) {
            throw new IllegalArgumentException("不支持contentType");
        }
        if (contentType.contains("application/json")) {
            return jsonResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }
        if (contentType.contains("application/x-www-form-urlencoded")) {
            return formResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }
        if (contentType.contains("multipart")) {
            return formResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }
        throw new IllegalArgumentException("不支持contentType");
    }
}
