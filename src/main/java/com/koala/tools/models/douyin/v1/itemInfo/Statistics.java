package com.koala.tools.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class Statistics implements Serializable {
    @SerializedName("admire_count")
    private Integer admireCount;
    @SerializedName("aweme_id")
    private String awemeId;
    @SerializedName("collect_count")
    private Long collectCount;
    @SerializedName("comment_count")
    private Long commentCount;
    @SerializedName("digg_count")
    private Long diggCount;
    @SerializedName("play_count")
    private Integer playCount;
    @SerializedName("share_count")
    private Long shareCount;
}