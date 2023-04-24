package com.koala.tools.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class LogPbModel implements Serializable {
    @SerializedName("impr_id")
    private String imprId;
}