package com.koala.tools.models.douyin.v1;

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
public class StickerDetailModel implements Serializable {
    private String children;
    @SerializedName("icon_url")
    private IconInfoModel iconInfoModel;
    private String id;
    private String name;
    @SerializedName("similar_sticker")
    private String similarSticker;
    private String tags;
    @SerializedName("user_count")
    private Integer userCount;
}