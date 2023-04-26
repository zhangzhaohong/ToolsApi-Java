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
public class LunaInfoModel implements Serializable {
    @SerializedName("has_copyright")
    private boolean hasCopyright;
    @SerializedName("is_luna_user")
    private boolean isLunaUser;
}