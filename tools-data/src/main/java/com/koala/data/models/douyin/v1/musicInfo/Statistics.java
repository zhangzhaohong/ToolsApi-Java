package com.koala.data.models.douyin.v1.musicInfo;

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
public class Statistics implements Serializable {
    @SerializedName("collect_count")
    private Long collectCount;
    @SerializedName("comment_count")
    private Long commentCount;
    private String digest;
    @SerializedName("digg_count")
    private Long diggCount;
    @SerializedName("exposure_count")
    private Integer exposureCount;
    @SerializedName("live_watch_count")
    private Integer liveWatchCount;
    @SerializedName("play_count")
    private Integer playCount;
    @SerializedName("share_count")
    private Long shareCount;
}