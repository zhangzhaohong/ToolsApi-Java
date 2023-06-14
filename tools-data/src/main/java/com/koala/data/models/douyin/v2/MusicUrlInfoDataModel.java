package com.koala.data.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:05
 * @description
 */
@Data
public class MusicUrlInfoDataModel implements Serializable {
    private String uri;
    @SerializedName("url_list")
    private ArrayList<String> urlList;
}
