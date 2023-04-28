package com.koala.tools.models.shortUrl;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/28 18:21
 * @description
 */
@Data
@AllArgsConstructor
public class ShortUrlInfoModel implements Serializable {
    private String url;
    private Long expire;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("expire_time")
    private String expireTime;
}
