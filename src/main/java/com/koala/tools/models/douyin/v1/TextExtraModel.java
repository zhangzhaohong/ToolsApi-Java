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
public class TextExtraModel implements Serializable {
    private int end;
    @SerializedName("hashtag_id")
    private String hashtagId;
    @SerializedName("hashtag_name")
    private String hashtagName;
    @SerializedName("is_commerce")
    private boolean isCommerce;
    private int start;
    private int type;
}
