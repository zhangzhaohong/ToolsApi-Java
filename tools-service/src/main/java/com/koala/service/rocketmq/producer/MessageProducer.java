package com.koala.service.rocketmq.producer;

import com.koala.service.rocketmq.RocketMqHelper;
import com.koala.service.rocketmq.data.TopicData;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/7 18:22
 * @description
 */
public class MessageProducer {
    public static void asyncSend(RocketMqHelper rocketMqHelper, String topic, String channel, Object obj) {
        rocketMqHelper.asyncSend(topic + ":" + Optional.ofNullable(channel).orElse(TopicData.DEFAULT_CHANNEL), MessageBuilder.withPayload(obj).build());
    }
}
