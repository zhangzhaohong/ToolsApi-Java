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
public class VideoTagModel implements Serializable {
    private Integer level;
    @SerializedName("tag_id")
    private Integer tagId;
    @SerializedName("tag_name")
    private String tagName;
}