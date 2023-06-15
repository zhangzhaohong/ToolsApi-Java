package com.koala.service.data.kafka.service;

import com.koala.service.data.kafka.model.MessageModel;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/13 11:37
 * @description
 */
public interface KafkaService {
    void send(MessageModel<?> message);
}
