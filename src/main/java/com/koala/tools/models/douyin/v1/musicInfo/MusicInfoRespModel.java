package com.koala.tools.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class MusicInfoRespModel implements Serializable {
    private List<AwemeMusicDetailModel> awemeMusicDetail;
    private Integer cursor;
    private Extra extra;
    @SerializedName("has_more")
    private Integer hasMore;
    @SerializedName("log_pb")
    private LogPbModel logPb;
    @SerializedName("status_code")
    private Integer statusCode;
}