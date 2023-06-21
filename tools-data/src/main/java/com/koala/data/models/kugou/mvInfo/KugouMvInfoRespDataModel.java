package com.koala.data.models.kugou.mvInfo;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.kugou.mvInfo.custom.PlayInfoModel;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/21 18:13
 * @description
 */
@Data
public class KugouMvInfoRespDataModel<Object extends Serializable> implements Serializable {
    private Integer status;
    @SerializedName("error_code")
    private Integer errorCode;
    private String errmsg;
    private Object data;
    @SerializedName("mv_info_data")
    private LinkedHashMap<String, PlayInfoModel> mvInfoData;
    @SerializedName("mock_preview_path")
    private LinkedHashMap<String, String> mockPreviewPath = null;
    @SerializedName("mock_download_path")
    private LinkedHashMap<String, String> mockDownloadPath = null;

    public LinkedHashMap<String, PlayInfoModel> getMvInfoData() {
        return Optional.ofNullable(mvInfoData).orElse(new LinkedHashMap<>());
    }
}
