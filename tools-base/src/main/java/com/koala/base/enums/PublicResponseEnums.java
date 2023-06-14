package com.koala.base.enums;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:38
 * @description
 */

import lombok.Getter;

@Getter
public enum PublicResponseEnums {
    FAILURE(-1, "UNKNOWN_ERROR"),
    INVALID_LINK(101, "INVALID_LINK"),
    GET_DATA_SUCCESS(200, "GET_DATA_SUCCESS");

    private final Integer code;
    private final String message;

    PublicResponseEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
