package com.koala.data.models.douyin.v1.roomInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:26
 * @description
 */
@Data
public class RoomOwnerInfoModel implements Serializable {
    @SerializedName("web_rid")
    private String webRid;
}