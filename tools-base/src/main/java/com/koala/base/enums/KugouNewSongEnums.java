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
public enum KugouNewSongEnums {
    TYPE_1("华语新歌", "1"),
    TYPE_2("欧美新歌", "2"),
    TYPE_3("日韩新歌", "3");

    private final String desc;
    private final String type;

    KugouNewSongEnums(String desc, String type) {
        this.desc = desc;
        this.type = type;
    }

    public static KugouNewSongEnums getEnumsByType(String type) {
        Optional<KugouNewSongEnums> optional = Arrays.stream(KugouNewSongEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
