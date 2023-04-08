package com.koala.tools.models.douyin.v1;

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
public class SeriesPaidInfoModel implements Serializable {
    @SerializedName("item_price")
    private Integer itemPrice;
    @SerializedName("series_paid_status")
    private Integer seriesPaidStatus;
}