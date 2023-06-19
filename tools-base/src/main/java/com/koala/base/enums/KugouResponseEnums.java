package com.koala.base.enums;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:38
 * @description
 */

import lombok.Getter;

@Getter
public enum KugouResponseEnums {
    FAILURE(-1, "UNKNOWN_ERROR"),
    INVALID_LINK(101, "INVALID_LINK"),
    INVALID_TYPE(102, "INVALID_TYPE"),
    GET_DATA_SUCCESS(200, "GET_DATA_SUCCESS"),
    GET_INFO_ERROR(201, "GET_INFO_ERROR"),
    UNSUPPORTED_OPERATION(202, "UNSUPPORTED_OPERATION"),
    UNSUPPORTED_TYPE(203, "UNSUPPORTED_TYPE"),
    UNSUPPORTED_PARAMS(204, "UNSUPPORTED_PARAMS"),
    GET_SIGNATURE_FAILED(205, "GET_SIGNATURE_FAILED"),
    UNAVAILABLE_PLAYER(500, "UNAVAILABLE_PLAYER"),
    UNAVAILABLE_DATA(501, "UNAVAILABLE_DATA");

    private final Integer code;
    private final String message;

    KugouResponseEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
