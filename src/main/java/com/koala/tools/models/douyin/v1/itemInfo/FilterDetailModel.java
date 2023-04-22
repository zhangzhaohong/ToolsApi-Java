package com.koala.tools.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 12:25
 * @description
 */
@Data
public class FilterDetailModel implements Serializable {
    @SerializedName("aweme_id")
    private String awemeId;
    @SerializedName("detail_msg")
    private String detailMsg;
    @SerializedName("filter_reason")
    private String filterReason;
    private String icon;
    private String notice;
}
