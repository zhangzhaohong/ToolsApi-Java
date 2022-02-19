package com.koala.tools.enums;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:38
 * @description
 */

import lombok.Getter;

@Getter
public enum DouYinResponseEnums {
    FAILURE(-1, "UNKNOWN_ERROR"),
    INVALID_URL(101, "INVALID_URL"),
    GET_INFO_ERROR(201, "GET_INFO_ERROR");

    private final Integer code;
    private final String message;

    DouYinResponseEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
