package com.koala.data.models.kugou.playInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:45
 * @description
 */
@Data
public class KugouPlayInfoRespDataModel {
    @SerializedName("ext_name")
    private String extName;
    @SerializedName("trans_param")
    private TransParamModel transParam;
    @SerializedName("file_head")
    private Integer fileHead;
    private Integer status;
    @SerializedName("file_size")
    private Long fileSize;
    @SerializedName("bit_rate")
    private Long bitRate;
    private Double volume;
    @SerializedName("volume_peak")
    private Double volumePeak;
    @SerializedName("volume_gain")
    private Integer volumeGain;
    private List<String> url;
    @SerializedName("file_name")
    private String fileName;
    @SerializedName("time_length")
    private Integer timeLength;
    private Integer q;
}