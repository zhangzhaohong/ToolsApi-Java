package com.koala.tools.models.douyin.v1.musicInfo;

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
public class DiggLottieModel implements Serializable {
    @SerializedName("can_bomb")
    private Integer canBomb;
    @SerializedName("lottie_id")
    private String lottieId;
}