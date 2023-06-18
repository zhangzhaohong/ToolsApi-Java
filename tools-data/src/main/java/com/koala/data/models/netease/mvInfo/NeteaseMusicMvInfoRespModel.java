package com.koala.data.models.netease.mvInfo;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.netease.MultiMvQualityInfoModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class NeteaseMusicMvInfoRespModel implements Serializable {
    private String loadingPic;
    private String bufferPic;
    private String loadingPicFS;
    private String bufferPicFS;
    private Boolean subed;
    private DataModel data;
    private Integer code;
    @SerializedName("mock_preview_path")
    private String mockPreviewPath = null;
    @SerializedName("mock_multi_download_path")
    private MultiMvQualityInfoModel mockMultiDownloadPath = null;
}