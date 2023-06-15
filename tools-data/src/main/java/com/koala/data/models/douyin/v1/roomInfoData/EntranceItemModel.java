package com.koala.data.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class EntranceItemModel implements Serializable {
    @SerializedName("group_id")
    private Integer groupId;
    @SerializedName("component_type")
    private Integer componentType;
    @SerializedName("op_type")
    private Integer opType;
    private String text;
    @SerializedName("schema_url")
    private String schemaUrl;
    @SerializedName("show_type")
    private Integer showType;
    @SerializedName("data_status")
    private Integer dataStatus;
    private String extra;
}