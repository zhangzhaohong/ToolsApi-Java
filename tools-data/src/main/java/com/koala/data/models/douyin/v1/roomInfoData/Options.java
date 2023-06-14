package com.koala.data.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class Options implements Serializable {
    @SerializedName("default_quality")
    private QualityModel defaultQuality;
    private List<QualityModel> qualities;
}