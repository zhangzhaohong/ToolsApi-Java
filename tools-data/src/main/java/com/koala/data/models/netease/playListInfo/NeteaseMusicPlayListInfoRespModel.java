package com.koala.data.models.netease.playListInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/18 18:50
 * @description
 */
@Data
public class NeteaseMusicPlayListInfoRespModel<Object extends Serializable> implements Serializable {
    private Object result;
    private Integer code;
}
