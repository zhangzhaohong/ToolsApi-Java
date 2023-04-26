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
public class HotListModel implements Serializable {
    private String extra;
    private String footer;
    @SerializedName("group_id")
    private String groupId;
    private String header;
    @SerializedName("hot_score")
    private Integer hotScore;
    @SerializedName("i18n_title")
    private String i18nTitle;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("pattern_type")
    private Integer patternType;
    private Integer rank;
    private String schema;
    private String sentence;
    @SerializedName("sentence_id")
    private Long sentenceId;
    private String title;
    private Integer type;
    @SerializedName("view_count")
    private Long viewCount;
}