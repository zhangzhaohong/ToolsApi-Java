package com.koala.tools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/8 17:59
 * @description
 */
@RestController
@RequestMapping("analytics/statistics/")
public class AnalyticsController {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsController.class);

    @GetMapping("/getData")
    public String getData() {
        return "";
    }
}
