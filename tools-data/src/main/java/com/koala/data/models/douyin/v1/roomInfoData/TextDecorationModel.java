package com.koala.data.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class TextDecorationModel implements Serializable {
    private Integer id;
    private Image image;
    private Integer type;
    @SerializedName("input_rect")
    private List<Integer> inputRect;
    @SerializedName("text_size")
    private Integer textSize;
    @SerializedName("text_color")
    private String textColor;
    private String content;
    @SerializedName("max_length")
    private Integer maxLength;
    private Integer status;
    private Integer h;
    private Integer x;
    private Integer w;
    private Integer y;
    private Integer kind;
    @SerializedName("sub_type")
    private Integer subType;
    @SerializedName("nine_patch_image")
    private Image ninePatchImage;
    @SerializedName("text_special_effects")
    private List<String> textSpecialEffects;
    @SerializedName("text_image_adjustable_start_position")
    private Integer textImageAdjustableStartPosition;
    @SerializedName("text_image_adjustable_end_position")
    private Integer textImageAdjustableEndPosition;
    @SerializedName("audit_text_color")
    private String auditTextColor;
}