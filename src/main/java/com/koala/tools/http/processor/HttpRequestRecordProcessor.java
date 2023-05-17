package com.koala.tools.http.processor;

import com.koala.tools.kafka.model.apiData.ApiDataTable;
import com.koala.tools.kafka.model.MessageModel;
import com.koala.tools.kafka.service.KafkaService;
import com.koala.tools.models.statistics.StatisticsData;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.RemoteIpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Resource(name = "KafkaService")
    private KafkaService kafkaService;

    /**
     * 带有@TakeTime注解的方法
     * com.example.executiontimedemo.interfaces.TakeTimeRecord （TakeTimeRecord 注解的包路径）
     */
    @Pointcut("@annotation(com.koala.tools.http.annotation.HttpRequestRecorder)")
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
        ApiDataTable apiData = new ApiDataTable(
                Objects.isNull(response) ? -1 : response.getStatus(),
                request.getServletPath(),
                request.getMethod(),
                System.currentTimeMillis() - startTime,
                GsonUtil.toString(new StatisticsData(ip))
        );
        kafkaService.send(new MessageModel<>(null, apiData));
    }
}
