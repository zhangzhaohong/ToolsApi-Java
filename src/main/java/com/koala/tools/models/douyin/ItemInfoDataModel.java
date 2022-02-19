package com.koala.tools.models.douyin;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 14:37
 * @description
 */
@Data
public class ItemInfoDataModel implements Serializable {
    @SerializedName("aweme_type")
    private Integer awemeType;
    private MusicItemInfoDataModel music;
    private VideoItemInfoDataModel video;
    private ArrayList<ImageItemInfoDataModel> images;
    private String desc;
    private AuthorInfoDataModel author;
}
