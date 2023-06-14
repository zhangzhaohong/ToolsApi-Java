package com.koala.data.models.douyin.v1.musicInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class ItemWarnNotificationModel implements Serializable {
    private String content;
    private Boolean show;
    private Integer type;
}