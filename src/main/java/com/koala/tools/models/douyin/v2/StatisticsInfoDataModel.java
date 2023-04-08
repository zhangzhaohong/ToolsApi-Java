package com.koala.tools.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 21:17
 * @description
 */
@Data
public class StatisticsInfoDataModel implements Serializable {
    @SerializedName("play_count")
    private Long playCount;
    @SerializedName("share_count")
    private Long shareCount;
    @SerializedName("aweme_id")
    private String awemeId;
    @SerializedName("comment_count")
    private Long commentCount;
    @SerializedName("digg_count")
    private Long diggCount;
}
