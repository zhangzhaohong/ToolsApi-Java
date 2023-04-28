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
    INVALID_LINK(101, "INVALID_LINK"),
    INVALID_TYPE(102, "INVALID_TYPE"),
    GET_DATA_SUCCESS(200, "GET_DATA_SUCCESS"),
    GET_INFO_ERROR(201, "GET_INFO_ERROR"),
    UNSUPPORTED_OPERATION(202, "UNSUPPORTED_OPERATION"),
    UNAVAILABLE_PLAYER(500, "UNAVAILABLE_PLAYER"),
    UNAVAILABLE_DATA(501, "UNAVAILABLE_DATA");

    private final Integer code;
    private final String message;

    DouYinResponseEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
