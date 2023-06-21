package com.koala.data.models.kugou.albumMusicInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:18
 * @description
 */
@Data
public class KugouAlbumMusicInfoRespDataModel<Object extends Serializable> implements Serializable {
    private Integer status;
    @SerializedName("error_code")
    private Integer errorCode;
    private String errmsg;
    private Object data;
}
