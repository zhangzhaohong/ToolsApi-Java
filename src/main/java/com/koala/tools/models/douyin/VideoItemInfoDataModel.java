package com.koala.tools.models.douyin;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 14:43
 * @description
 */
@Data
public class VideoItemInfoDataModel implements Serializable {
    private String ratio;
    private String vid;
    private Integer width;
    private Integer height;
    private Integer duration;
    private VideoCoverInfoDataModel cover;
    private String realPath;
    private String mockPath;
}
