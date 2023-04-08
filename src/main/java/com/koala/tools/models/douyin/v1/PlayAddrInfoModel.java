package com.koala.tools.models.douyin.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class PlayAddrInfoModel implements Serializable {
    @SerializedName("data_size")
    private Long dataSize;
    @SerializedName("file_cs")
    private String fileCs;
    @SerializedName("file_hash")
    private String fileHash;
    private Integer height;
    private String uri;
    @SerializedName("url_key")
    private String urlKey;
    @SerializedName("url_list")
    private List<String> urlList;
    private Integer width;
}