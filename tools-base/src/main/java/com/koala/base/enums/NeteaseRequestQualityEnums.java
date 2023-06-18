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
public enum NeteaseRequestQualityEnums {
    DEFAULT("standard", 0, null),
    STANDARD("standard", 1, "标准音质"),
    EX_HIGH("exhigh", 2, "极高音质"),
    LOSSLESS("lossless", 3, "无损音质"),
    HIRES("hires", 4, "Hires音质");

    private final String type;
    private final int typeId;
    private final String desc;

    NeteaseRequestQualityEnums(String type, int typeId, String desc) {
        this.type = type;
        this.typeId = typeId;
        this.desc = desc;
    }

    public static int getTypeIdByType(String type) {
        Optional<NeteaseRequestQualityEnums> optional = Arrays.stream(NeteaseRequestQualityEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.map(NeteaseRequestQualityEnums::getTypeId).orElse(-1);
    }

    public static NeteaseRequestQualityEnums getEnumsByType(String type) {
        Optional<NeteaseRequestQualityEnums> optional = Arrays.stream(NeteaseRequestQualityEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
