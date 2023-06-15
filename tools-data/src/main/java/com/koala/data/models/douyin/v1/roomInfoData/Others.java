package com.koala.data.models.douyin.v1.roomInfoData;

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
public class Others implements Serializable {
    @SerializedName("deco_detail")
    private DecoDetailModel decoDetail;
    @SerializedName("more_panel_info")
    private MorePanelInfoModel morePanelInfo;
    @SerializedName("appointment_info")
    private AppointmentInfoModel appointmentInfo;
    @SerializedName("web_skin")
    private WebSkinModel webSkin;
    private Programme programme;
    @SerializedName("live_matrix_info")
    private LiveMatrixInfoModel liveMatrixInfo;
    @SerializedName("web_live_port_optimization")
    private WebLivePortOptimizationModel webLivePortOptimization;
}