package com.koala.data.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class Image implements Serializable {
    @SerializedName("url_list")
    private List<String> urlList;
    private String uri;
    private Integer height;
    private Integer width;
    @SerializedName("avg_color")
    private String avgColor;
    @SerializedName("image_type")
    private Integer imageType;
    @SerializedName("open_web_url")
    private String openWebUrl;
    @SerializedName("is_animated")
    private Boolean isAnimated;
    @SerializedName("flex_setting_list")
    private List<String> flexSettingList;
    @SerializedName("text_setting_list")
    private List<String> textSettingList;
}