package com.koala.tools.controller;

import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.demo.TestModel;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("test/get")
    public String testGet(@MixedHttpRequest String p) {
        return p;
    }

    @PostMapping("test/post")
    public Object testPost(@MixedHttpRequest TestModel model) {
        return model.getP();
    }
}
