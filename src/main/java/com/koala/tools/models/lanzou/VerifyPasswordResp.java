package com.koala.tools.models.lanzou;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/14 12:05
 * @description
 */
@Data
public class VerifyPasswordResp implements Serializable {
    private Integer zt;
    @SerializedName("dom")
    private String host;
    @SerializedName("url")
    private String path;
    @SerializedName("inf")
    private String fileName;
}
