package com.koala.data.models.netease.itemInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class NeteaseMusicItemInfoRespModel implements Serializable {
    private List<DataModel<?>> data;
    private Integer code;
}