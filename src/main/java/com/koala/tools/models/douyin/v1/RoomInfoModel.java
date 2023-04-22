package com.koala.tools.models.douyin.v1;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:26
 * @description
 */
@Data
public class RoomInfoModel implements Serializable {
    private RoomOwnerInfoModel owner;
}
