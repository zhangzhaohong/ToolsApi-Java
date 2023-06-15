package com.koala.data.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class AwemeControlModel implements Serializable {
    @SerializedName("can_comment")
    private Boolean canComment;
    @SerializedName("can_forward")
    private Boolean canForward;
    @SerializedName("can_share")
    private Boolean canShare;
    @SerializedName("can_show_comment")
    private Boolean canShowComment;
}