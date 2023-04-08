package com.koala.tools.models.douyin.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class Video {
    @SerializedName("big_thumbs")
    private String bigThumbs;
    @SerializedName("bit_rate")
    private List<BitRateInfoModel> bitRateInfoModelList;
    @SerializedName("bit_rate_audio")
    private String bitRateAudio;
    @SerializedName("cover")
    private CoverInfoModel coverInfoModel;
    @SerializedName("cover_original_scale")
    private CoverInfoModel coverOriginalScale;
    private Integer duration;
    @SerializedName("dynamic_cover")
    private CoverInfoModel dynamicCover;
    private Integer height;
    @SerializedName("is_h265")
    private Integer isH265;
    @SerializedName("is_source_HDR")
    private Integer isSourceHDR;
    private String meta;
    @SerializedName("optimized_cover")
    private CoverInfoModel optimizedCover;
    @SerializedName("origin_cover")
    private CoverInfoModel originCover;
    @SerializedName("play_addr")
    private PlayAddrInfoModel playAddrInfoModel;
    @SerializedName("play_addr_265")
    private PlayAddrInfoModel playAddr265;
    @SerializedName("play_addr_h264")
    private PlayAddrInfoModel playAddrH264;
    private String ratio;
    private int width;
}