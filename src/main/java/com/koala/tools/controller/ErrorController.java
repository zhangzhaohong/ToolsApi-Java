package com.koala.tools.controller;

import com.koala.tools.http.annotation.HttpRequestRecorder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String pageForbidden(@RequestParam(value = "notice", required = false) String notice, Model model) {
        model.addAttribute("notice", notice);
        return "403/index";
    }

    @HttpRequestRecorder
    @GetMapping("404")
    public String pageNotFound() {
        return "404/index";
    }

    @HttpRequestRecorder
    @GetMapping("500")
    public String internalServerError() {
        return "500/index";
    }
}
