package com.koala.service.custom.http.processor;

import com.koala.data.models.statistics.StatisticsData;
import com.koala.service.data.kafka.model.MessageModel;
import com.koala.service.data.kafka.model.apiData.ApiData;
import com.koala.service.data.kafka.service.KafkaService;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.RemoteIpUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/17 17:56
 * @description
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class HttpRequestRecordProcessor {

    @Resource(name = "ApiAnalyticsKafkaService")
    private KafkaService kafkaService;

    /**
     * 带有@TakeTime注解的方法
     * com.example.executiontimedemo.interfaces.TakeTimeRecord （TakeTimeRecord 注解的包路径）
     */
    @Pointcut("@annotation(com.koala.service.custom.http.annotation.HttpRequestRecorder)")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void doBefore() {
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        request.setAttribute("_startTime", System.currentTimeMillis());

    }

    @After("pointcut()")
    public void doAfterReturning() {
        //接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        long startTime = (Long) request.getAttribute("_startTime");
        String ip = RemoteIpUtils.getRemoteIpByServletRequest(request, true);
        ApiData apiData = new ApiData(
                Objects.isNull(response) ? -1 : response.getStatus(),
                request.getServletPath(),
                request.getMethod(),
                System.currentTimeMillis() - startTime,
                GsonUtil.toString(new StatisticsData(ip))
        );
        kafkaService.send(new MessageModel<>(null, apiData));
    }
}
