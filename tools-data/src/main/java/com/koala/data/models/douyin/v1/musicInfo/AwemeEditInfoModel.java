package com.koala.data.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class AwemeEditInfoModel implements Serializable {
    @SerializedName("button_status")
    private Integer buttonStatus;
    @SerializedName("button_toast")
    private String buttonToast;
}