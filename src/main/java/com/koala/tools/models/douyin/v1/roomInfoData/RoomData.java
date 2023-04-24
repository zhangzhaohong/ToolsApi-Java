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
public class RoomData<Object extends Serializable> implements Serializable {
    private List<RoomItemInfoDataModel> data;
    @SerializedName("enter_room_id")
    private String enterRoomId;
    private Extra extra;
    private User user;
    @SerializedName("qrcode_url")
    private String qrcodeUrl;
    @SerializedName("enter_mode")
    private Integer enterMode;
    @SerializedName("room_status")
    private Integer roomStatus;
    @SerializedName("partition_road_map")
    private PartitionRoadMapModel partitionRoadMap;
    @SerializedName("similar_rooms")
    private Object similarRooms;
    @SerializedName("shark_decision_conf")
    private String sharkDecisionConf;
    @SerializedName("web_stream_url")
    private StreamUrlModel webStreamUrl;
    @SerializedName("login_lead")
    private LoginLeadModel loginLead;
}