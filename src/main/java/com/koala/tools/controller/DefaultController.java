package com.koala.tools.controller;

import com.koala.tools.http.annotation.MixedHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 16:23
 * @description demo
 */
@RestController
@RequestMapping("demo")
public class DefaultController {
    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("test")
    public String test(@MixedHttpRequest String p) {
        return p;
    }
}
