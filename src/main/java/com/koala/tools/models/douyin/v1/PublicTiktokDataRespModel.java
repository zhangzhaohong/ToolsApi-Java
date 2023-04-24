package com.koala.tools.models.douyin.v1;

import com.google.gson.annotations.SerializedName;
import com.koala.tools.models.douyin.v1.itemInfo.ItemInfoRespModel;
import com.koala.tools.models.douyin.v1.roomInfoData.RoomInfoDataRespModel;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/23 13:29
 * @description
 */
@Data
@AllArgsConstructor
public class PublicTiktokDataRespModel {
    @SerializedName("item_type_id")
    private Integer itemTypeId;
    @SerializedName("item_info_data")
    private ItemInfoRespModel itemInfoData;
    @SerializedName("room_item_info_data")
    private RoomInfoDataRespModel roomItemInfoData;
}
