package com.koala.tools.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 14:43
 * @description
 */
@Data
public class VideoItemInfoDataModel implements Serializable {
    private String ratio;
    private String vid;
    private Integer width;
    private Integer height;
    private Integer duration;
    private VideoCoverInfoDataModel cover;
    @SerializedName("real_path")
    private String realPath;
    @SerializedName("mock_preview_vid_path")
    private String mockPreviewVidPath;
    @SerializedName("mock_download_vid_path")
    private String mockDownloadVidPath;
}
