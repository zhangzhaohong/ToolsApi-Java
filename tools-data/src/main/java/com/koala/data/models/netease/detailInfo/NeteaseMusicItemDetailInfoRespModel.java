package com.koala.data.models.netease.detailInfo;

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
public class NeteaseMusicItemDetailInfoRespModel implements Serializable {
    private List<SongModel<?>> songs;
    private List<PrivilegeModel> privileges;
    private Integer code;
}