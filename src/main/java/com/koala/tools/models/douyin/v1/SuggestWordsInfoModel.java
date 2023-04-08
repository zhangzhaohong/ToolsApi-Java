package com.koala.tools.models.douyin.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/8 14:47
 * @description
 */
@Data
public class SuggestWordsInfoModel implements Serializable {
    @SerializedName("extra_info")
    private String extraInfo;
    @SerializedName("hint_text")
    private String hintText;
    @SerializedName("icon_url")
    private String iconUrl;
    private String scene;
    private List<SuggestWordInfoModel> words;
}
