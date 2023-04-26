package com.koala.tools.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class Icon implements Serializable {
    private Integer height;
    private String uri;
    @SerializedName("url_key")
    private String urlKey;
    @SerializedName("url_list")
    private List<String> urlList;
    private Integer width;
}