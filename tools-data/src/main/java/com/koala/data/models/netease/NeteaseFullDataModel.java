package com.koala.data.models.netease;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.netease.detailInfo.NeteaseMusicItemDetailInfoRespModel;
import com.koala.data.models.netease.itemInfo.NeteaseMusicItemInfoRespModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 13:11
 * @description
 */
@Data
@AllArgsConstructor
public class NeteaseFullDataModel implements Serializable {
    @SerializedName("item_info")
    private NeteaseMusicItemInfoRespModel itemInfo;
    @SerializedName("detail_info")
    private NeteaseMusicItemDetailInfoRespModel detailInfo;
}
