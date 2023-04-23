package com.koala.tools.models.douyin.v1.roomInfoData;

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
public class SpecialStyleModel implements Serializable {
    @SerializedName("UnableStyle")
    private Integer unableStyle;
    @SerializedName("Content")
    private String content;
    @SerializedName("OffType")
    private Integer offType;
}