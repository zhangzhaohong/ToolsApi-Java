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
public class PullUrlModel implements Serializable {
    @SerializedName("FULL_HD1")
    private String fullHd1;
    @SerializedName("HD1")
    private String hd1;
    @SerializedName("SD1")
    private String sd1;
    @SerializedName("SD2")
    private String sd2;
}