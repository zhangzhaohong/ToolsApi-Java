package com.koala.web.controller;

import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.custom.http.annotation.MixedHttpRequest;
import com.koala.data.models.demo.TestModel;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.data.rocketmq.RocketMqHelper;
import com.koala.service.data.rocketmq.data.TopicData;
import com.koala.service.data.rocketmq.model.DemoModel;
import com.koala.service.data.rocketmq.producer.MessageProducer;
import com.koala.service.utils.GsonUtil;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Set;

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

    @Resource
    private RocketMqHelper rocketMqHelper;

    @Resource(name = "RedisService")
    private RedisService redisService;

    @HttpRequestRecorder
    @GetMapping("hello")
    public String hello() {
        return "Hello";
    }

    @HttpRequestRecorder
    @GetMapping("test/get")
    public String testGet(@MixedHttpRequest String p) {
        return p;
    }

    @HttpRequestRecorder
    @PostMapping("test/post")
    public Object testPost(@RequestBody TestModel model) {
        return model.getP();
    }

    @HttpRequestRecorder
    @PostMapping(value = "test/post/x-www-form-urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public Object testPostXWWW(@RequestParam String p) {
        return GsonUtil.toString(p);
    }

    @HttpRequestRecorder
    @GetMapping("redis/set")
    public String setRedis(@NonNull @MixedHttpRequest String key, @NonNull @MixedHttpRequest String value) {
        redisService.set(key, value);
        return "ok";
    }

    @HttpRequestRecorder
    @GetMapping("redis/get")
    public String getRedis(@NonNull @MixedHttpRequest String key) {
        return redisService.get(key);
    }

    @HttpRequestRecorder
    @GetMapping("redis/setList")
    public String setListRedis(@NonNull @MixedHttpRequest String key, @NonNull @MixedHttpRequest String value) {
        redisTemplate.opsForList().leftPush(key, value);
        return "ok";
    }

    @HttpRequestRecorder
    @GetMapping("redis/getList")
    public List<String> getListRedis(@NonNull @MixedHttpRequest String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @HttpRequestRecorder
    @GetMapping("redis/set/input")
    public String setInput(@MixedHttpRequest String key, @MixedHttpRequest String value) {
        redisTemplate.opsForSet().add(key, value);
        return "ok";
    }

    @HttpRequestRecorder
    @GetMapping("redis/set/checkMember")
    public Boolean setCheckMember(@MixedHttpRequest String key, @MixedHttpRequest String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @HttpRequestRecorder
    @GetMapping("redis/set/get")
    public Set<String> setGet(@MixedHttpRequest String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @HttpRequestRecorder
    @GetMapping("mq/test/c1")
    public String pushMqC1() {
        MessageProducer.asyncSend(rocketMqHelper, TopicData.DEMO, TopicData.DEMO_CHANNEL_1, new DemoModel(System.currentTimeMillis(), "Hello world"));
        return "ok";
    }

    @HttpRequestRecorder
    @GetMapping("mq/test/c2")
    public String pushMqC2() {
        MessageProducer.asyncSend(rocketMqHelper, TopicData.DEMO, TopicData.DEMO_CHANNEL_2, new DemoModel(System.currentTimeMillis(), "Hello world"));
        return "ok";
    }

}
