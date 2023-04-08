package com.koala.tools.models.douyin.v2;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 14:34
 * @description
 */
@Data
public class ItemInfoRespModel implements Serializable {
    @SerializedName("status_code")
    private Integer statusCode;
    @SerializedName("item_list")
    private ArrayList<ItemInfoDataModel> itemList = new ArrayList<>(0);
}
