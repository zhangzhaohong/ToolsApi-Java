package com.koala.base.enums;

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
    MUSIC_TYPE("music", 8, "/music/"),
    LIVE_TYPE_1("live_1", 7, "live.douyin.com"),
    LIVE_TYPE_2("live_2", 6, "/webcast/reflow/"),
    NOTE_TYPE("note", 5, "/note/"),
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
        return optional.orElse(null);
    }
}
