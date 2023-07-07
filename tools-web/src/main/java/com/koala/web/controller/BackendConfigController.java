package com.koala.web.controller;

import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.RespUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.koala.service.data.redis.RedisKeyPrefix.SERVICE_HOST;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/10 12:07
 * @description
 */
@RestController
@RequestMapping("backend")
public class BackendConfigController {

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.build.time}")
    private String buildTime;

    @Value("${spring.profiles.active}")
    private String env;

    @Resource(name = "getHost")
    private String host;

    @Resource
    private RedisService redisService;

    @HttpRequestRecorder
    @GetMapping("info")
    public String info() {
        HashMap<String, String> info = new HashMap<>(0);
        info.put("version", version);
        info.put("buildTime", buildTime);
        info.put("env", env);
        return RespUtil.formatRespDataWithCustomMsg(
                200,
                "GET_INFO_SUCCESS",
                info
        );
    }

    @HttpRequestRecorder
    @GetMapping("config/service/host/set")
    public String setServiceHost(@RequestParam(required = false) String host) {
        redisService.set(SERVICE_HOST, host);
        this.host = redisService.getAndPersist(SERVICE_HOST);
        return RespUtil.formatRespDataWithCustomMsg(
                200,
                "SET_HOST_SUCCESS",
                this.host
        );
    }

    @HttpRequestRecorder
    @GetMapping("config/service/host/get")
    public String getServiceHost() {
        return RespUtil.formatRespDataWithCustomMsg(
                200,
                "GET_HOST_SUCCESS",
                host
        );
    }
}
