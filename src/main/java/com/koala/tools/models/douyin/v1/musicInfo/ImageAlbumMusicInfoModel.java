package com.koala.tools.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class ImageAlbumMusicInfoModel implements Serializable {
    @SerializedName("begin_time")
    private Integer beginTime;
    @SerializedName("end_time")
    private Integer endTime;
    private Integer volume;
}