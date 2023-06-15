package com.koala.data.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class SeriesPaidInfoModel implements Serializable {
    @SerializedName("item_price")
    private Integer itemPrice;
    @SerializedName("series_paid_status")
    private Integer seriesPaidStatus;
}