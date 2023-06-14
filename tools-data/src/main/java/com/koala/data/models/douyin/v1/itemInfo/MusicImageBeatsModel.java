package com.koala.data.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class MusicImageBeatsModel implements Serializable {
    @SerializedName("music_image_beats_url")
    private MusicImageBeatsInfoModel musicImageBeatsInfoModel;
}