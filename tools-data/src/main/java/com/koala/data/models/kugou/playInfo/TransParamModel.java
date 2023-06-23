package com.koala.data.models.kugou.playInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:45
 * @description
 */
@Data
public class TransParamModel implements Serializable {
    private ClassmapModel classmap;
    private Integer display;
    @SerializedName("display_rate")
    private Integer displayRate;
}