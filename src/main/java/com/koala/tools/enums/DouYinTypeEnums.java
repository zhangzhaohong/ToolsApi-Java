package com.koala.tools.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 19:07
 * @description
 */
@Getter
public enum DouYinTypeEnums {
    VIDEO_TYPE("video", 4, "/video/"),
    IMAGE_TYPE("image", 2, null);

    private final String type;
    private final Integer code;
    private final String prefix;

    DouYinTypeEnums(String type, Integer code, String prefix) {
        this.type = type;
        this.code = code;
        this.prefix = prefix;
    }

    public static DouYinTypeEnums getEnumsByCode(Integer code) {
        Optional<DouYinTypeEnums> optional = Arrays.stream(DouYinTypeEnums.values()).filter(item -> item.getCode().equals(code)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
