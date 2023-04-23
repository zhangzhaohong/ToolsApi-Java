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
public class LinkerDetailModel implements Serializable {
    @SerializedName("linker_play_modes")
    private List<Integer> linkerPlayModes;
    @SerializedName("big_party_layout_config_version")
    private Integer bigPartyLayoutConfigVersion;
    @SerializedName("accept_audience_pre_apply")
    private Boolean acceptAudiencePreApply;
    @SerializedName("linker_ui_layout")
    private Integer linkerUiLayout;
    @SerializedName("enable_audience_linkmic")
    private Integer enableAudienceLinkmic;
    @SerializedName("function_type")
    private String functionType;
    @SerializedName("linker_map_str")
    private LinkerMapStrModel linkerMapStr;
    @SerializedName("ktv_lyric_mode")
    private String ktvLyricMode;
    @SerializedName("init_source")
    private String initSource;
    @SerializedName("forbid_apply_from_other")
    private Boolean forbidApplyFromOther;
    @SerializedName("ktv_exhibit_mode")
    private Integer ktvExhibitMode;
    @SerializedName("enlarge_guest_turn_on_source")
    private Integer enlargeGuestTurnOnSource;
    @SerializedName("playmode_detail")
    private PlaymodeDetailModel playModeDetail;
}