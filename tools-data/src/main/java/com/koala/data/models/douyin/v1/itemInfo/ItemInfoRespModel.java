package com.koala.data.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class ItemInfoRespModel implements Serializable {
    @SerializedName("aweme_detail")
    private AwemeDetailModel<?> awemeDetailModel;
    @SerializedName("filter_detail")
    private FilterDetailModel filterDetailModel = null;
    @SerializedName("log_pb")
    private LogPbModel logPb;
    @SerializedName("status_code")
    private Integer statusCode;
}