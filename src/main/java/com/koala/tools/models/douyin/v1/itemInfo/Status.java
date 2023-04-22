package com.koala.tools.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import com.koala.tools.models.douyin.v1.itemInfo.ReviewResultModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class Status implements Serializable {
    @SerializedName("allow_share")
    private Boolean allowShare;
    @SerializedName("aweme_id")
    private String awemeId;
    @SerializedName("in_reviewing")
    private Boolean inReviewing;
    @SerializedName("is_delete")
    private Boolean isDelete;
    @SerializedName("is_prohibited")
    private Boolean isProhibited;
    @SerializedName("listen_video_status")
    private Integer listenVideoStatus;
    @SerializedName("part_see")
    private Integer partSee;
    @SerializedName("private_status")
    private Integer privateStatus;
    @SerializedName("review_result")
    private ReviewResultModel reviewResultModel;
}