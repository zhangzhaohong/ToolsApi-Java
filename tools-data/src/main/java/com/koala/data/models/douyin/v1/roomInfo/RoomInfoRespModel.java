package com.koala.data.models.douyin.v1.roomInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:17
 * @description 只取出web rid
 */
@Data
public class RoomInfoRespModel implements Serializable {
    private RoomInfoDataModel data;
}
