package com.koala.data.models.shortUrl;

import com.koala.data.models.netease.MultiMvQualityInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/28 11:33
 * @description
 */
@Data
@AllArgsConstructor
public class ShortNeteaseMvItemDataModel implements Serializable {
    private String title;
    private String path;
    private MultiMvQualityInfoModel multiMvQualityInfo;
    private final String type = "mp4";
}
