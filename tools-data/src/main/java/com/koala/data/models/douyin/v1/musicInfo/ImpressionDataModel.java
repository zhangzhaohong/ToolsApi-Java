package com.koala.data.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class ImpressionDataModel implements Serializable {
    @SerializedName("group_id_list_a")
    private List<String> groupIdListA;
    @SerializedName("group_id_list_b")
    private List<String> groupIdListB;
    @SerializedName("group_id_list_c")
    private List<Long> groupIdListC;
    @SerializedName("similar_id_list_a")
    private String similarIdListA;
    @SerializedName("similar_id_list_b")
    private List<Long> similarIdListB;
}