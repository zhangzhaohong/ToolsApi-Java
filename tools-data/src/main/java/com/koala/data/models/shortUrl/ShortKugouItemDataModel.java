package com.koala.data.models.shortUrl;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.kugou.albumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
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
public class ShortKugouItemDataModel implements Serializable {
    private String title;
    @SerializedName("author_name")
    private String authorName;
    @SerializedName("music_info")
    private KugouAlbumCustomMusicInfoModel musicInfo;
}
