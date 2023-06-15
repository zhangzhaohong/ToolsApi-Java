package com.koala.data.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class SuggestWordsModel implements Serializable {
    @SerializedName("suggest_words")
    private List<SuggestWordsInfoModel> suggestWords;
}