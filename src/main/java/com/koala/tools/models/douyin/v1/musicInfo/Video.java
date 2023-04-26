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
public class Video<Object extends Serializable> implements Serializable {
    @SerializedName("big_thumbs")
    private Object bigThumbs;
    @SerializedName("bit_rate")
    private List<BitRateInfoModel> bitRateInfoModelList;
    @SerializedName("bit_rate_audio")
    private String bitRateAudio;
    @SerializedName("cover")
    private CoverInfoModel coverInfoModel;
    private Integer duration;
    @SerializedName("dynamic_cover")
    private CoverInfoModel dynamicCover;
    private Integer height;
    @SerializedName("is_bytevc1")
    private Integer isBytevc1;
    @SerializedName("is_callback")
    private Boolean isCallback;
    @SerializedName("is_source_HDR")
    private Integer isSourceHDR;
    private String meta;
    @SerializedName("origin_cover")
    private CoverInfoModel originCover;
    @SerializedName("play_addr")
    private PlayAddrInfoModel playAddrInfoModel;
    @SerializedName("play_addr_265")
    private PlayAddrInfoModel playAddr265;
    @SerializedName("play_addr_h264")
    private PlayAddrInfoModel playAddrH264;
    private String ratio;
    private String tags;
    @SerializedName("video_model")
    private String videoModel;
    private Integer width;
}