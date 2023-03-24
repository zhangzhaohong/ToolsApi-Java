package com.koala.tools.http.processor;

import com.koala.tools.http.annotation.MixedHttpRequest;
import jakarta.servlet.ServletRequest;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 11:22
 * @description
 */
@DependsOn({"beanContext"})
public class MixedHttpRequestProcessor implements HandlerMethodArgumentResolver {

    @Resource(name = "customRequestMappingHandlerAdapter")
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    private final Map<String, HandlerMethodArgumentResolver> argumentResolverCache =
            new ConcurrentHashMap<>(8);

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        MixedHttpRequest mixedHttpRequest = methodParameter.getMethodAnnotation(MixedHttpRequest.class);
        return (!Objects.isNull(mixedHttpRequest));
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletRequest servletRequest = nativeWebRequest.getNativeRequest(ServletRequest.class);
        if (Objects.isNull(servletRequest)) {
            throw new IllegalArgumentException("servletRequest不能为null");
        }
        String contentType = servletRequest.getContentType();
        if (Objects.isNull(contentType)) {
            throw new IllegalArgumentException("无效的contentType");
        }
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        HandlerMethodArgumentResolver handlerMethodArgumentResolver = argumentResolverCache.get(contentType);
        if (!Objects.isNull(handlerMethodArgumentResolver)) {
            return handlerMethodArgumentResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
        }
        if (Objects.isNull(argumentResolvers)) {
            throw new IllegalArgumentException("无效的resolvers");
        }
        for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
            if (contentType.contains("application/json") && argumentResolver instanceof RequestResponseBodyMethodProcessor) {
                argumentResolverCache.put(contentType, argumentResolver);
                return argumentResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
            }
            if (contentType.contains("application/x-www-form-urlencoded") && argumentResolver instanceof ServletModelAttributeMethodProcessor) {
                argumentResolverCache.put(contentType, argumentResolver);
                return argumentResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
            }
            if (contentType.contains("multipart") && argumentResolver instanceof ServletModelAttributeMethodProcessor) {
                argumentResolverCache.put(contentType, argumentResolver);
                return argumentResolver.resolveArgument(methodParameter, modelAndViewContainer, nativeWebRequest, webDataBinderFactory);
            }
        }
        throw new IllegalArgumentException("不支持contentType");
    }
}
