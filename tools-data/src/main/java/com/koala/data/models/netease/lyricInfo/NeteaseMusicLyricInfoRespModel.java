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
public class NeteaseMusicLyricInfoRespModel implements Serializable {
    private Boolean sgc;
    private Boolean sfy;
    private Boolean qfy;
    private LrcModel lrc;
    private KlyricModel klyric;
    private TlyricModel tlyric;
    private Integer code;
}