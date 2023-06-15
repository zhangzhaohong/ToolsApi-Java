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
public class GuideSceneInfoModel implements Serializable {
    @SerializedName("diamond_expose_info_str")
    private String diamondExposeInfoStr;
    @SerializedName("feed_origin_gid_info_str")
    private String feedOriginGidInfoStr;
    @SerializedName("guide_scene_type")
    private Integer guideSceneType;
}