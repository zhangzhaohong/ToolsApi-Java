package com.koala.tools.kafka.service.impl;

import com.koala.tools.kafka.model.MessageModel;
import com.koala.tools.kafka.service.KafkaService;
import com.koala.tools.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/13 11:35
 * @description
 */
@Service("KafkaService")
@Slf4j
public class KafkaServiceImpl implements KafkaService {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_STR = "api-analytics-process";

    @Async
    @Override
    public void send(MessageModel<?> message) {
        String obj2String = GsonUtil.toString(message);
        log.info("[Kafka] on prepared message：{}", obj2String);
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_STR, message);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(final SendResult<String, Object> message) {
                log.info("[Kafka] on sent message = " + message + " with offset = " + message.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(@NotNull final Throwable throwable) {
                log.error("[Kafka] unable to send message = " + message, throwable);
            }
        });
    }
}
