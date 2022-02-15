package com.koala.tools.models.lanzou;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/15 12:48
 * @description
 */
@Data
public class FolderFileInfoRespModel implements Serializable {
    private String icon;
    private Integer t;
    private String id;
    @SerializedName("name_all")
    private String fileName;
    @SerializedName("size")
    private String fileSize;
    @SerializedName("time")
    private String updateTime;
    @SerializedName("duan")
    private String extra;
    @SerializedName("p_ico")
    private Integer ico;
}
