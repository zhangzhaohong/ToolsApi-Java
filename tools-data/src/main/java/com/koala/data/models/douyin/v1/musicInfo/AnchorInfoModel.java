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
public class AnchorInfoModel implements Serializable {
    private String content;
    private String extra;
    private Icon icon;
    private String id;
    @SerializedName("log_extra")
    private String logExtra;
    @SerializedName("mp_url")
    private String mpUrl;
    @SerializedName("open_url")
    private String openUrl;
    @SerializedName("style_info")
    private StyleInfoModel styleInfo;
    private String title;
    @SerializedName("title_tag")
    private String titleTag;
    private Integer type;
    @SerializedName("web_url")
    private String webUrl;
}