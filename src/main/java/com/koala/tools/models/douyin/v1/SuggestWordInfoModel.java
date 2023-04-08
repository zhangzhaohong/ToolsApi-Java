package com.koala.tools.models.douyin.v1;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/8 14:50
 * @description
 */
@Data
public class SuggestWordInfoModel implements Serializable {
    private String info;
    private String word;
    @SerializedName("word_id")
    private String wordId;
}
