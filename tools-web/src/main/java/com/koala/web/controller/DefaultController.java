package com.koala.web.controller;

import com.koala.service.rocketmq.RocketMqHelper;
import com.koala.service.rocketmq.data.TopicData;
import com.koala.service.rocketmq.model.DemoModel;
import com.koala.service.rocketmq.producer.MessageProducer;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/14 12:58
 * @description
 */
@RestController
@RequestMapping("/demo")
public class DefaultController {

    @Resource
    private RocketMqHelper rocketMqHelper;

    //    @HttpRequestRecorder
    @GetMapping("mq/test/c1")
    public String pushMqC1() {
        MessageProducer.asyncSend(rocketMqHelper, TopicData.DEMO, TopicData.DEMO_CHANNEL_1, new DemoModel(System.currentTimeMillis(), "Hello world"));
        return "ok";
    }

    //    @HttpRequestRecorder
    @GetMapping("mq/test/c2")
    public String pushMqC2() {
        MessageProducer.asyncSend(rocketMqHelper, TopicData.DEMO, TopicData.DEMO_CHANNEL_2, new DemoModel(System.currentTimeMillis(), "Hello world"));
        return "ok";
    }
}
