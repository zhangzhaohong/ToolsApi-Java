package com.koala.tools.models.douyin.v1;

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
public class CommentPermissionInfoModel implements Serializable {
    @SerializedName("can_comment")
    private Boolean canComment;
    @SerializedName("comment_permission_status")
    private Integer commentPermissionStatus;
    @SerializedName("item_detail_entry")
    private Boolean itemDetailEntry;
    @SerializedName("press_entry")
    private Boolean pressEntry;
    @SerializedName("toast_guide")
    private Boolean toastGuide;
}