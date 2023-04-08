package com.koala.tools.controller;

import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.demo.TestModel;
import com.koala.tools.redis.service.RedisService;
import com.koala.tools.rocketmq.data.TopicData;
import com.koala.tools.rocketmq.model.DemoModel;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.rocketmq.RocketMqHelper;
import lombok.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
        redisService.set(key, value);
        return "ok";
    }

    @GetMapping("redis/get")
    public String getRedis(@NonNull @MixedHttpRequest String key) {
        return redisService.get(key);
    }

    @GetMapping("redis/setList")
    public String setListRedis(@NonNull @MixedHttpRequest String key, @NonNull @MixedHttpRequest String value) {
        redisTemplate.opsForList().leftPush(key, value);
        return "ok";
    }

    @GetMapping("redis/getList")
    public List<String> getListRedis(@NonNull @MixedHttpRequest String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    @GetMapping("redis/set/input")
    public String setInput(@MixedHttpRequest String key, @MixedHttpRequest String value) {
        redisTemplate.opsForSet().add(key, value);
        return "ok";
    }

    @GetMapping("redis/set/checkMember")
    public Boolean setCheckMember(@MixedHttpRequest String key, @MixedHttpRequest String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    @GetMapping("redis/set/get")
    public Set<String> setGet(@MixedHttpRequest String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @GetMapping("mq/test/c1")
    public String pushMqC1() {
        rocketMqHelper.asyncSend(TopicData.DEMO + ":" + TopicData.DEMO_CHANNEL_1, MessageBuilder.withPayload(new DemoModel(System.currentTimeMillis(), "Hello world")).build());
        return "ok";
    }

    @GetMapping("mq/test/c2")
    public String pushMqC2() {
        rocketMqHelper.asyncSend(TopicData.DEMO + ":" + TopicData.DEMO_CHANNEL_2, MessageBuilder.withPayload(new DemoModel(System.currentTimeMillis(), "Hello world")).build());
        return "ok";
    }

}
