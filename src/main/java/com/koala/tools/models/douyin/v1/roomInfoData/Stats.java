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
public class Stats implements Serializable {
    @SerializedName("total_user_desp")
    private String totalUserDesp;
    @SerializedName("like_count")
    private Integer likeCount;
    @SerializedName("total_user_str")
    private String totalUserStr;
    @SerializedName("user_count_str")
    private String userCountStr;
}