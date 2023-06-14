package com.koala.data.models.douyin.v1.itemInfo;

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
public class ShareAuthorInfoModel implements Serializable {
    @SerializedName("share_desc")
    private String shareDesc;
    @SerializedName("share_desc_info")
    private String shareDescInfo;
    @SerializedName("share_link_desc")
    private String shareLinkDesc;
    @SerializedName("share_url")
    private String shareUrl;
}