package com.koala.data.models.kugou.mvInfo.custom;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 22:00
 * @description
 */
@Data
@AllArgsConstructor
public class PlayInfoModel implements Serializable {
    private Long bitrate;
    private String hash;
    @SerializedName("file_size")
    private Long filesize;
}
