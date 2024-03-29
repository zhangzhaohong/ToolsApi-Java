package com.koala.data.models.kugou;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.kugou.albumInfo.KugouAlbumInfoRespDataModel;
import com.koala.data.models.kugou.albumMusicInfo.KugouAlbumMusicInfoRespDataModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:45
 * @description
 */
@Data
@AllArgsConstructor
public class KugouMusicDataRespModel<Object extends Serializable> implements Serializable {
    @SerializedName("album_info")
    private KugouAlbumInfoRespDataModel<?> albumInfo;
    @SerializedName("album_music_info")
    private KugouAlbumMusicInfoRespDataModel<?> albumMusicInfo;
    @SerializedName("music_info_data")
    private KugouAlbumCustomMusicInfoModel musicInfoData;
    @SerializedName("lyric_info_data")
    private Map<String, Object> lyricInfoData;
    @SerializedName("mock_preview_path")
    private LinkedHashMap<String, String> mockPreviewPath = null;
    @SerializedName("mock_download_path")
    private LinkedHashMap<String, String> mockDownloadPath = null;
}
