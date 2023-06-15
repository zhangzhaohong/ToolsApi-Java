package com.koala.data.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/27 21:03
 * @description
 */
@Data
public class ImageItemDataModel implements Serializable {
    @SerializedName("download_url_list")
    private List<String> downloadUrlList;
    private int height;
    private String uri;
    @SerializedName("url_list")
    private List<String> urlList;
    private int width;
}
