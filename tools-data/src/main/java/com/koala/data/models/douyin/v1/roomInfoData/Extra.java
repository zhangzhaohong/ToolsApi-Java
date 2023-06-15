package com.koala.data.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class Extra implements Serializable {
    private Integer height;
    private Integer width;
    private Integer fps;
    @SerializedName("max_bitrate")
    private Integer maxBitrate;
    @SerializedName("min_bitrate")
    private Integer minBitrate;
    @SerializedName("default_bitrate")
    private Integer defaultBitrate;
    @SerializedName("bitrate_adapt_strategy")
    private Integer bitrateAdaptStrategy;
    @SerializedName("anchor_interact_profile")
    private Integer anchorInteractProfile;
    @SerializedName("audience_interact_profile")
    private Integer audienceInteractProfile;
    @SerializedName("hardware_encode")
    private Boolean hardwareEncode;
    @SerializedName("video_profile")
    private Integer videoProfile;
    @SerializedName("h265_enable")
    private Boolean h265Enable;
    @SerializedName("gop_sec")
    private Integer gopSec;
    @SerializedName("bframe_enable")
    private Boolean bframeEnable;
    private Boolean roi;
    @SerializedName("sw_roi")
    private Boolean swRoi;
    @SerializedName("bytevc1_enable")
    private Boolean bytevc1Enable;
}