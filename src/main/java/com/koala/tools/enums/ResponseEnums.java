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
    INVALID_TYPE(102, "INVALID_TYPE"),
    GET_FILE_SUCCESS(200, "GET_FILE_SUCCESS"),
    GET_FILE_ERROR(201, "GET_FILE_ERROR"),
    GET_FILE_ERROR_WITH_PASSWORD(202, "GET_FILE_ERROR_WITH_PASSWORD"),
    GET_FILE_WITH_PASSWORD(203, "GET_FILE_WITH_PASSWORD"),
    HAS_MORE_FILE(204, "HAS_MORE_FILE"),
    GET_DATA_ERROR(299, "GET_DATA_ERROR");

    private final int code;
    private final String message;

    ResponseEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
