package com.koala.tools.models.douyin.v1;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class ItemWarnNotificationModel implements Serializable {
    private String content;
    private Boolean show;
    private Integer type;
}