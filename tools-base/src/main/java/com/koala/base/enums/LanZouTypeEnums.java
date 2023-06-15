package com.koala.base.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 17:00
 * @description
 */
@Getter
public enum LanZouTypeEnums {
    DOWNLOAD("download", 1),
    INFO("info", 0),
    INVALID_TYPE("invalid_type", -1);

    private final String type;
    private final int typeId;

    LanZouTypeEnums(String type, int typeId) {
        this.type = type;
        this.typeId = typeId;
    }

    public static int getTypeIdByType(String type) {
        Optional<LanZouTypeEnums> optional = Arrays.stream(LanZouTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.map(LanZouTypeEnums::getTypeId).orElse(-1);
    }

    public static LanZouTypeEnums getEnumsByType(String type) {
        Optional<LanZouTypeEnums> optional = Arrays.stream(LanZouTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
