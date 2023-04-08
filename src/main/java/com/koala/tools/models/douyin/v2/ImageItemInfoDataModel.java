package com.koala.tools.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:00
 * @description
 */
@Data
public class ImageItemInfoDataModel implements Serializable {
    private String uri;
    @SerializedName("url_list")
    private ArrayList<String> urlList = new ArrayList<>(0);
    private Integer width;
    private Integer height;
}
