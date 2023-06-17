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
public enum NeteaseTypeEnums {
    DOWNLOAD("download", 1),
    INFO("info", 0),
    PREVIEW_MUSIC("preview_music", 2),
    PREVIEW_MV("preview_mv", 3),
    INVALID_TYPE("invalid_type", -1);

    private final String type;
    private final int typeId;

    NeteaseTypeEnums(String type, int typeId) {
        this.type = type;
        this.typeId = typeId;
    }

    public static int getTypeIdByType(String type) {
        Optional<NeteaseTypeEnums> optional = Arrays.stream(NeteaseTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.map(NeteaseTypeEnums::getTypeId).orElse(-1);
    }

    public static NeteaseTypeEnums getEnumsByType(String type) {
        Optional<NeteaseTypeEnums> optional = Arrays.stream(NeteaseTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
