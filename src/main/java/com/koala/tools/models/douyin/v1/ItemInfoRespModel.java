package com.koala.tools.models.douyin.v1;

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
    private AwemeDetailModel awemeDetailModel;
    @SerializedName("log_pb")
    private LogPbModel logPb;
    @SerializedName("status_code")
    private int statusCode;
}