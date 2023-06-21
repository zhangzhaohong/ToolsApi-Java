package com.koala.data.models.kugou.AlbumMusicInfo.custom;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:18
 * @description
 */
@Data
public class AudioInfoModel implements Serializable {
    @SerializedName("is_file_head")
    private String isFileHead;
    @SerializedName("is_file_head_320")
    private String isFileHead320;
    @SerializedName("audio_id")
    private String audioId;
    @SerializedName("audio_group_id")
    private String audioGroupId;
    @SerializedName("extname_super")
    private String extnameSuper;
    private String extname;
    @SerializedName("play_info_list")
    private HashMap<String, PlayInfoModel> playInfoList;

    public HashMap<String, PlayInfoModel> getPlayInfoList() {
        return Optional.ofNullable(this.playInfoList).orElse(new HashMap<>());
    }
}