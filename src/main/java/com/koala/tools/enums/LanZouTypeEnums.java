package com.koala.tools.enums;

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
    INFO("info", 0);

    private final String type;
    private final int typeId;

    LanZouTypeEnums(String type, int typeId) {
        this.type = type;
        this.typeId = typeId;
    }

    public int getTypeIdByType(String type) {
        Optional<LanZouTypeEnums> optional = Arrays.stream(LanZouTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        if (optional.isPresent()) {
            return optional.get().getTypeId();
        }
        return -1;
    }
}
