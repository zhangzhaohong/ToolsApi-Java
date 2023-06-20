package com.koala.data.models.kugou.AlbumMusicInfo.pattern;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:18
 * @description
 */
@Data
public class AlbumInfoModel implements Serializable {
    @SerializedName("album_id")
    private String albumId;
    @SerializedName("album_name")
    private String albumName;
    @SerializedName("publish_date")
    private Date publishDate;
    private String category;
    @SerializedName("is_publish")
    private String isPublish;
    @SerializedName("sizable_cover")
    private String sizableCover;
}