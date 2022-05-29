package com.koala.tools.controller;

import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.demo.TestModel;
import com.koala.tools.utils.GsonUtil;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 16:23
 * @description demo
 */
@RestController
@RequestMapping("demo")
public class DefaultController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

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
    @ResponseBody
    public Object testPostXWWW(@RequestParam String p) {
        return GsonUtil.toString(p);
    }

    @GetMapping("redis/set")
    public String setRedis(@NonNull @MixedHttpRequest String key, @NonNull @MixedHttpRequest String value) {
        redisTemplate.opsForValue().set(key, value);
        return "ok";
    }

    @GetMapping("redis/get")
    public String getRedis(@NonNull @MixedHttpRequest String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
