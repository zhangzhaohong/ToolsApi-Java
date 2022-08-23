package com.koala.tools.models.pixiee;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/20 11:42
 * @description
 */
@Data
@AllArgsConstructor
public class ProductInfoModel implements Serializable {
    private String description;
    private String modelInfo;
    private String material;
    @SerializedName("package")
    private String packageInfo;
    private  String pockets;
    private String type;
    private String caring;
}
