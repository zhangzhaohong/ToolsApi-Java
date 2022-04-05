package com.koala.tools.controller;

import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.demo.TestModel;
import com.koala.tools.utils.GsonUtil;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
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
    public Object testPost(@RequestBody TestModel model) {
        return model.getP();
    }

    @PostMapping(value = "test/post/x-www-form-urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Object testPostXWWW(@RequestParam(value = "p") String p) {
        return GsonUtil.toString(p);
    }
}
