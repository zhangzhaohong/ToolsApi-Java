package com.koala.data.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:04
 * @description
 */
@Data
public class MusicItemInfoDataModel implements Serializable {
    private String title;
    @SerializedName("play_url")
    private MusicUrlInfoDataModel playUrl;
    private Long id;
    private String mid;
    private Integer duration;
    private Integer status;
    private String author;
    @SerializedName("cover_hd")
    private MusicCoverInfoDataModel coverHd;
}
