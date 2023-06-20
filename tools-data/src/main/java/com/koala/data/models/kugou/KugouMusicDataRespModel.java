package com.koala.data.models.kugou;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.kugou.AlbumInfo.KugouAlbumInfoRespDataModel;
import com.koala.data.models.kugou.AlbumMusicInfo.KugouAlbumMusicInfoRespDataModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:45
 * @description
 */
@Data
@AllArgsConstructor
public class KugouMusicDataRespModel implements Serializable {
    @SerializedName("album_info")
    private KugouAlbumInfoRespDataModel<?> albumInfo;
    @SerializedName("album_music_info")
    private KugouAlbumMusicInfoRespDataModel<?> albumMusicInfo;
}
