package com.koala.tools.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class Song implements Serializable {
    private String artists;
    @SerializedName("chorus_v3_infos")
    private String chorusV3Infos;
    private Long id;
    @SerializedName("id_str")
    private String idStr;
}