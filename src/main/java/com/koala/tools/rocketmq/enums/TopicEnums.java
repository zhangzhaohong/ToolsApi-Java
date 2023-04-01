package com.koala.tools.rocketmq.enums;

import lombok.Getter;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 17:00
 * @description
 */
@Getter
public enum TopicEnums {
    DEMO("demo");

    private final String topicName;

    TopicEnums(String topicName) {
        this.topicName = topicName;
    }
}
