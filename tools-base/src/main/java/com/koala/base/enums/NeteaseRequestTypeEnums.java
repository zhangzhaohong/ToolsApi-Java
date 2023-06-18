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
public enum NeteaseRequestTypeEnums {
    DOWNLOAD("download", 1),
    INFO("info", 0),
    PREVIEW_MUSIC("preview_music", 2),
    PREVIEW_MV("preview_mv", 3),
    INVALID_TYPE("invalid_type", -1);

    private final String type;
    private final int typeId;

    NeteaseRequestTypeEnums(String type, int typeId) {
        this.type = type;
        this.typeId = typeId;
    }

    public static int getTypeIdByType(String type) {
        Optional<NeteaseRequestTypeEnums> optional = Arrays.stream(NeteaseRequestTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.map(NeteaseRequestTypeEnums::getTypeId).orElse(-1);
    }

    public static NeteaseRequestTypeEnums getEnumsByType(String type) {
        Optional<NeteaseRequestTypeEnums> optional = Arrays.stream(NeteaseRequestTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
