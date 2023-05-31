package com.koala.tools.webSocket.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/31 20:53
 * @description
 */
@Getter
public enum Constants {
    HEARTBREAK("HeartBreak", 999);

    private final String event;
    private final int code;

    Constants(String event, int code) {
        this.event = event;
        this.code = code;
    }

    public static int getCodeByEvent(String event) {
        Optional<Constants> optional = Arrays.stream(Constants.values()).filter(item -> item.getEvent().equals(event)).findFirst();
        return optional.map(Constants::getCode).orElse(-1);
    }

    public static String getEventByCode(Integer code) {
        Optional<Constants> optional = Arrays.stream(Constants.values()).filter(item -> String.valueOf(item.getCode()).equals(code.toString())).findFirst();
        return optional.map(Constants::getEvent).orElse(null);
    }

    public static Constants getEnumByEvent(String event) {
        Optional<Constants> optional = Arrays.stream(Constants.values()).filter(item -> item.getEvent().equals(event)).findFirst();
        return optional.orElse(null);
    }
}
