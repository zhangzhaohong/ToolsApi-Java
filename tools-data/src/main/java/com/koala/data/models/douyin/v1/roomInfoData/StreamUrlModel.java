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
public class StreamUrlModel implements Serializable {
    @SerializedName("flv_pull_url")
    private PullUrlModel flvPullUrl;
    @SerializedName("default_resolution")
    private String defaultResolution;
    @SerializedName("hls_pull_url_map")
    private PullUrlModel hlsPullUrlMap;
    @SerializedName("hls_pull_url")
    private String hlsPullUrl;
    @SerializedName("stream_orientation")
    private Integer streamOrientation;
    @SerializedName("live_core_sdk_data")
    private LiveCoreSdkDataModel liveCoreSdkData;
    private Extra extra;
    @SerializedName("pull_datas")
    private PullDatasModel pullDatas;
    @SerializedName("mock_preview_live_path")
    private String mockPreviewLivePath = null;
    @SerializedName("mock_preview_live_path_backup")
    private String mockPreviewLivePathBackup = null;
}