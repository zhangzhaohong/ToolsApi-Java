package com.koala.base.enums;

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
public enum NeteaseRankIdEnums {
    RANK_1("1", "19723756", "云音乐飙升榜"),
    RANK_2("2", "3779629", "云音乐新歌榜"),
    RANK_3("3", "3778678", "云音乐热歌榜"),
    RANK_4("4", "2250011882", "抖音排行榜");

    private final String id;
    private final String rankId;
    private final String desc;

    NeteaseRankIdEnums(String id, String rankId, String desc) {
        this.id = id;
        this.rankId = rankId;
        this.desc = desc;
    }

    public static NeteaseRankIdEnums getEnumsById(String id) {
        Optional<NeteaseRankIdEnums> optional = Arrays.stream(NeteaseRankIdEnums.values()).filter(item -> item.getId().equals(id)).findFirst();
        return optional.orElse(null);
    }
}
