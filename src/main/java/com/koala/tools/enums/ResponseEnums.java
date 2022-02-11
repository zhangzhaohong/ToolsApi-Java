package com.koala.tools.enums;

import lombok.Getter;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 17:17
 * @description
 */
@Getter
public enum ResponseEnums {
    SUCCESS(0, null),
    FAILURE(-1, "KNOWN_ERROR"),
    INVALID_URL(101, "INVALID_URL"),
    INVALID_TYPE(102, "INVALID_TYPE");

    private final int code;
    private final String message;

    ResponseEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
