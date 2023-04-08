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
public class ShareInfoModel implements Serializable {
    @SerializedName("share_desc")
    private String shareDesc;
    @SerializedName("share_desc_info")
    private String shareDescInfo;
    @SerializedName("share_qrcode_url")
    private ShareQrCodeInfoModel shareQrCodeInfoModel;
    @SerializedName("share_title")
    private String shareTitle;
    @SerializedName("share_title_myself")
    private String shareTitleMyself;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("share_weibo_desc")
    private String shareWeiboDesc;
}