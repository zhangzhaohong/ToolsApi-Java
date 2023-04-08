package com.koala.tools.models.douyin.v2;

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
public class AuthorInfoDataModel implements Serializable {
    @SerializedName("unique_id")
    private String uniqueId;
    private String uid;
    @SerializedName("short_id")
    private String shortId;
    @SerializedName("nickname")
    private String nickName;
    private String signature;
    @SerializedName("avatar_thumb")
    private AuthorUrlInfoDataModel avatarThumb;
}
