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
public class ToolbarDataModel implements Serializable {
    @SerializedName("entrance_list")
    private List<EntranceItemModel> entranceList;
    @SerializedName("more_panel")
    private List<EntranceItemModel> morePanel;
    @SerializedName("max_entrance_cnt")
    private Integer maxEntranceCnt;
    @SerializedName("landscape_up_right")
    private List<String> landscapeUpRight;
    @SerializedName("skin_resource")
    private SkinResourceModel skinResource;
    @SerializedName("max_entrance_cnt_landscape")
    private Integer maxEntranceCntLandscape;
}