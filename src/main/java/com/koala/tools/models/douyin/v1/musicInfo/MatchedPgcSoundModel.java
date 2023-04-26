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
public class MatchedPgcSoundModel implements Serializable {
    private String author;
    @SerializedName("cover_medium")
    private CoverInfoModel coverMedium;
    @SerializedName("mixed_author")
    private String mixedAuthor;
    @SerializedName("mixed_title")
    private String mixedTitle;
    private String title;
}