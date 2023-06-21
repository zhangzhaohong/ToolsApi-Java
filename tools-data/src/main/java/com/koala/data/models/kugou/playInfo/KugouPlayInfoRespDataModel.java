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
    private String extName;
    @SerializedName("trans_param")
    private TransParamModel transParam;
    private Integer fileHead;
    private Integer status;
    private Long fileSize;
    private Long bitRate;
    private Double volume;
    @SerializedName("volume_peak")
    private Double volumePeak;
    @SerializedName("volume_gain")
    private Integer volumeGain;
    private List<String> url;
    private String fileName;
    private Integer timeLength;
    private Integer q;
}