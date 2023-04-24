
package com.koala.tools.models.douyin.v1.itemInfo;
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
public class ShareQrCodeInfoModel implements Serializable {
    private int height;
    private String uri;
    @SerializedName("url_list")
    private List<String> urlList;
    private int width;
}