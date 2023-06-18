
package com.koala.data.models.netease.mvInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class NeteaseMusicMvInfoRespModel implements Serializable {
    private String loadingPic;
    private String bufferPic;
    private String loadingPicFS;
    private String bufferPicFS;
    private Boolean subed;
    private DataModel data;
    private Integer code;
}