package com.koala.tools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 16:30
 * @description
 */
@RestController
@RequestMapping("tools")
public class ToolsController {

    public static final Logger logger = LoggerFactory.getLogger(ToolsController.class);

    @GetMapping("LanZou/api")
    public Objects getLanZouInfos(
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "type", required = false, defaultValue = "info") String type
    ) {
        logger.info("LanZouApi: params: url: {}, password: {}, type: {}", url, password, type);
        if (Objects.isNull(url)) {

        }
        return null;
    }
}
