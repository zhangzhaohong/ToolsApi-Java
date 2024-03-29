package com.koala.data.models.kugou.albumMusicInfo.custom;

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
    private String bitrate;
    private String hash;
    @SerializedName("file_size")
    private String filesize;
    @SerializedName("time_length")
    private String timeLength;
}
