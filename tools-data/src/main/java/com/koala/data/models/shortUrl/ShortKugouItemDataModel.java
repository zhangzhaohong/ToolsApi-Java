package com.koala.data.models.shortUrl;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.kugou.albumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
import com.koala.data.models.kugou.mvInfo.custom.MvInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Optional;

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
    @SerializedName("mv_info")
    private ArrayList<MvInfoModel> mvInfo;

    public ArrayList<MvInfoModel> getMvInfo() {
        return Optional.ofNullable(mvInfo).orElse(new ArrayList<>());
    }
}
