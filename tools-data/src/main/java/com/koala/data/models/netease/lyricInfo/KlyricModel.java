package com.koala.data.models.netease.lyricInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class KlyricModel implements Serializable {
    private Integer version;
    private String lyric;
}