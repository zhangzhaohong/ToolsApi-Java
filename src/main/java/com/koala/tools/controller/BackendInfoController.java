package com.koala.tools.controller;

import com.koala.tools.http.annotation.HttpRequestRecorder;
import com.koala.tools.utils.RespUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/10 12:07
 * @description
 */
@RestController
@RequestMapping("backend")
public class BackendInfoController {

    @Value("${spring.application.version}")
    private String version;

    @Value("${spring.application.build.time}")
    private String buildTime;

    @Value("${spring.profiles.active}")
    private String env;

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
}
