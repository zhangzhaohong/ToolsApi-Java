package com.koala.tools.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class RoomInfoDataRespModel implements Serializable {
    @SerializedName("status_code")
    private Integer statusCode;
    private RoomData<?> data;
    private Extra extra;
}