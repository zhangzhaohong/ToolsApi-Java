package com.koala.data.models.lanzou;

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
public class VerifyPasswordRespModel implements Serializable {
    private Integer zt;
    @SerializedName("dom")
    private String downloadHost;
    @SerializedName("url")
    private String downloadPath;
    @SerializedName("inf")
    private String fileName;
}
