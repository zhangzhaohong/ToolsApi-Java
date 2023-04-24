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
public class AdmireAuthModel implements Serializable {
    @SerializedName("admire_button")
    private Integer admireButton;
    @SerializedName("is_admire")
    private Integer isAdmire;
    @SerializedName("is_click_admire_icon_recently")
    private Integer isClickAdmireIconRecently;
    @SerializedName("is_fifty_admire_author_stable_fans")
    private Integer isFiftyAdmireAuthorStableFans;
    @SerializedName("is_show_admire_button")
    private Integer isShowAdmireButton;
    @SerializedName("is_show_admire_tab")
    private Integer isShowAdmireTab;
}