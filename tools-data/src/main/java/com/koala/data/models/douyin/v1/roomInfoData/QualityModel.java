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
public class QualityModel implements Serializable {
    private String name;
    @SerializedName("sdk_key")
    private String sdkKey;
    @SerializedName("v_codec")
    private String vCodec;
    private String resolution;
    private Integer level;
    @SerializedName("v_bit_rate")
    private Integer vBitRate;
    @SerializedName("additional_content")
    private String additionalContent;
    private Integer fps;
    private Integer disable;
}