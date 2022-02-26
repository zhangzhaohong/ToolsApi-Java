package com.koala.tools.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/26 22:13
 * @description
 */
@Getter
public enum DouYinRequestTypeEnums {
    PREVIEW("preview", 2),
    DOWNLOAD("download", 1),
    INFO("info", 0),
    INVALID_TYPE("invalid_type", -1);

    private final String type;
    private final int typeId;

    DouYinRequestTypeEnums(String type, int typeId) {
        this.type = type;
        this.typeId = typeId;
    }

    public static int getTypeIdByType(String type) {
        Optional<DouYinRequestTypeEnums> optional = Arrays.stream(DouYinRequestTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        if (optional.isPresent()) {
            return optional.get().getTypeId();
        }
        return -1;
    }

    public static DouYinRequestTypeEnums getEnumsByType(String type) {
        Optional<DouYinRequestTypeEnums> optional = Arrays.stream(DouYinRequestTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
