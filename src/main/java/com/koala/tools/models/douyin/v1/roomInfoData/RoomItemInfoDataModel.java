package com.koala.tools.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class RoomItemInfoDataModel implements Serializable {
    @SerializedName("id_str")
    private String idStr;
    private Integer status;
    @SerializedName("status_str")
    private String statusStr;
    private String title;
    @SerializedName("user_count_str")
    private String userCountStr;
    private Cover cover;
    @SerializedName("stream_url")
    private StreamUrlModel streamUrl;
    @SerializedName("mosaic_status")
    private Integer mosaicStatus;
    @SerializedName("mosaic_status_str")
    private String mosaicStatusStr;
    @SerializedName("admin_user_ids")
    private List<Long> adminUserIds;
    @SerializedName("admin_user_ids_str")
    private List<String> adminUserIdsStr;
    private Owner owner;
    @SerializedName("room_auth")
    private RoomAuthModel roomAuth;
    @SerializedName("live_room_mode")
    private Integer liveRoomMode;
    private Stats stats;
    @SerializedName("has_commerce_goods")
    private Boolean hasCommerceGoods;
    @SerializedName("linker_map")
    private LinkerMapModel linkerMap;
    @SerializedName("linker_detail")
    private LinkerDetailModel linkerDetail;
    @SerializedName("room_view_stats")
    private RoomViewStatsModel roomViewStats;
    @SerializedName("scene_type_info")
    private SceneTypeInfoModel sceneTypeInfo;
    @SerializedName("toolbar_data")
    private ToolbarDataModel toolbarData;
    @SerializedName("room_cart")
    private RoomCartModel roomCart;
    @SerializedName("AnchorABMap")
    private AnchorABMapModel anchorABMap;
    @SerializedName("like_count")
    private Integer likeCount;
    private Others others;
}