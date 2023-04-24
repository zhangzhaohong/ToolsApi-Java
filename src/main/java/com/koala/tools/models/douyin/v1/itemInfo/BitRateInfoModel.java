
package com.koala.tools.models.douyin.v1.itemInfo;

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
public class BitRateInfoModel implements Serializable {
    @SerializedName("FPS")
    private Integer fps;
    @SerializedName("HDR_bit")
    private String hdrBit;
    @SerializedName("HDR_type")
    private String hdrType;
    @SerializedName("bit_rate")
    private Long bitRate;
    @SerializedName("gear_name")
    private String gearName;
    @SerializedName("is_bytevc1")
    private Integer isBytevc1;
    @SerializedName("is_h265")
    private Integer isH265;
    @SerializedName("play_addr")
    private PlayAddrInfoModel playAddrInfoModel;
    @SerializedName("quality_type")
    private Integer qualityType;
    @SerializedName("video_extra")
    private String videoExtra;
}