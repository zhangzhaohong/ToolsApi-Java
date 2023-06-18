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
public enum NeteaseRequestSearchTypeEnums {
    MUSIC_1("1", "单曲"),
    MUSIC_10("10", "专辑"),
    MUSIC_100("100", "歌手"),
    MUSIC_1000("1000", "歌单"),
    MUSIC_1002("1002", "用户"),
    MUSIC_1004("1004", "MV"),
    MUSIC_1006("1006", "歌词"),
    MUSIC_1009("1009", "主播电台");

    private final String type;
    private final String desc;

    NeteaseRequestSearchTypeEnums(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static NeteaseRequestSearchTypeEnums getEnumsByType(String type) {
        Optional<NeteaseRequestSearchTypeEnums> optional = Arrays.stream(NeteaseRequestSearchTypeEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
