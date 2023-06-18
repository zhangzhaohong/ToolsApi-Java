
package com.koala.data.models.netease.mvInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class BrsModel implements Serializable {
    @SerializedName("240")
    private String brs240;
    @SerializedName("480")
    private String brs480;
    @SerializedName("720")
    private String brs720;
    @SerializedName("1080")
    private String brs1080;
}