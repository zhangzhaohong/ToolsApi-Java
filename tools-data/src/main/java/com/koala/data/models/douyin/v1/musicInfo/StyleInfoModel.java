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
public class StyleInfoModel implements Serializable {
    @SerializedName("default_icon")
    private String defaultIcon;
    private String extra;
    @SerializedName("scene_icon")
    private String sceneIcon;
}