package com.koala.data.models.netease.detailInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class MediaModel implements Serializable {
    private Long br;
    private Integer fid;
    private Long size;
    private Integer vd;
    private Integer sr;
}