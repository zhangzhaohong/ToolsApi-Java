package com.koala.service.data.kafka.service.impl;

import com.koala.service.data.kafka.model.MessageModel;
import com.koala.service.data.kafka.service.KafkaService;
import com.koala.service.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/13 11:35
 * @description
 */
@Service("EventTrackerKafkaService")
@Slf4j
public class EventTrackerKafkaServiceImpl implements KafkaService {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_STR = "event-tracker-process";

    @Async
    @Override
    public void send(MessageModel<?> message) {
        String obj2String = GsonUtil.toString(message);
        log.info("[Kafka] on prepared message：{}", obj2String);
        //发送消息
        kafkaTemplate.send(TOPIC_STR, message).whenComplete((sendResult, throwable) -> {
            if (!Objects.isNull(throwable)) {
                log.info("[Kafka] on sent message = " + sendResult + " with offset = " + sendResult.getRecordMetadata().offset());
            } else {
                log.error("[Kafka] unable to send message = " + message, throwable);
            }
        });
    }
}
