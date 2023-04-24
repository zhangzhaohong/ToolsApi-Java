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
public class SceneTypeInfoModel implements Serializable {
    @SerializedName("is_union_live_room")
    private Boolean isUnionLiveRoom;
    @SerializedName("is_life")
    private Boolean isLife;
    @SerializedName("is_protected_room")
    private Integer isProtectedRoom;
    @SerializedName("is_lasted_goods_room")
    private Integer isLastedGoodsRoom;
    @SerializedName("is_desire_room")
    private Integer isDesireRoom;
    @SerializedName("commentary_type")
    private Boolean commentaryType;
}