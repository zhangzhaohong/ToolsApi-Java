package com.koala.tools.models.douyin.v1.itemInfo;

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
public class FeedCommentConfigModel implements Serializable {
    @SerializedName("author_audit_status")
    private Integer authorAuditStatus;
    @SerializedName("input_config_text")
    private String inputConfigText;
}