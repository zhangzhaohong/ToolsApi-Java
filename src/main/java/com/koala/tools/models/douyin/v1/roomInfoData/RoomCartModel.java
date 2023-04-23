package com.koala.tools.models.douyin.v1.roomInfoData;

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
public class RoomCartModel implements Serializable {
    @SerializedName("contain_cart")
    private Boolean containCart;
    private Integer total;
    @SerializedName("flash_total")
    private Integer flashTotal;
    @SerializedName("cart_icon")
    private String cartIcon;
    @SerializedName("show_cart")
    private Integer showCart;
}