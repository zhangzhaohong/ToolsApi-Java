package com.koala.tools.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 21:21
 * @description
 */
@Data
public class RiskInfoDataModel implements Serializable {
    private Boolean warn;
    private Integer type;
    private String content;
    @SerializedName("reflow_unplayable")
    private Integer reflowUnplayable;
}
