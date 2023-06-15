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
public class RoomViewStatsModel implements Serializable {
    @SerializedName("is_hidden")
    private Boolean isHidden;
    @SerializedName("display_short")
    private String displayShort;
    @SerializedName("display_middle")
    private String displayMiddle;
    @SerializedName("display_long")
    private String displayLong;
    @SerializedName("display_value")
    private Integer displayValue;
    @SerializedName("display_version")
    private Long displayVersion;
    private Boolean incremental;
    @SerializedName("display_type")
    private Integer displayType;
    @SerializedName("display_short_anchor")
    private String displayShortAnchor;
    @SerializedName("display_middle_anchor")
    private String displayMiddleAnchor;
    @SerializedName("display_long_anchor")
    private String displayLongAnchor;
}