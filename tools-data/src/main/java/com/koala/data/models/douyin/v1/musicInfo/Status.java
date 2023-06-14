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
public class Status implements Serializable {
    @SerializedName("allow_share")
    private Boolean allowShare;
    @SerializedName("aweme_edit_info")
    private AwemeEditInfoModel awemeEditInfo;
    @SerializedName("dont_share_status")
    private Integer dontShareStatus;
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
    private ReviewResultModel reviewResult;
    @SerializedName("video_hide_search")
    private Integer videoHideSearch;
}