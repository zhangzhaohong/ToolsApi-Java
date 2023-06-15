package com.koala.data.models.file;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/12 17:34
 * @description
 */
@Data
public class FileInfoModel implements Serializable {
    @SerializedName("file_name")
    private String fileName;
    @SerializedName("file_size")
    private String fileSize;
    @SerializedName("update_time")
    private String updateTime;
    private String author;
    @SerializedName("path_prefix")
    private String pathPrefix;
    @SerializedName("download_host")
    private String downloadHost;
    @SerializedName("download_path")
    private String downloadPath;
    @SerializedName("download_url")
    private String downloadUrl;
    @SerializedName("redirect_url")
    private String redirectUrl;
}
