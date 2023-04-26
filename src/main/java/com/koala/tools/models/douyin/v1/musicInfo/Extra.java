package com.koala.tools.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class Extra implements Serializable {
    @SerializedName("fatal_item_ids")
    private List<String> fatalItemIds;
    private String logid;
    private Long now;
}