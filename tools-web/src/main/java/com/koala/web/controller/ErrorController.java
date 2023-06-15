package com.koala.web.controller;

import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/17 22:30
 * @description
 */
@Controller
@RequestMapping("error")
public class ErrorController {

    @HttpRequestRecorder
    @GetMapping("403")
    public String pageForbidden(HttpServletResponse response) {
        response.setStatus(HttpStatus.SC_FORBIDDEN);
        return "403/index";
    }

    @HttpRequestRecorder
    @GetMapping("404")
    public String pageNotFound(HttpServletResponse response) {
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

    @HttpRequestRecorder
    @GetMapping("500")
    public String internalServerError(HttpServletResponse response) {
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return "500/index";
    }
}
