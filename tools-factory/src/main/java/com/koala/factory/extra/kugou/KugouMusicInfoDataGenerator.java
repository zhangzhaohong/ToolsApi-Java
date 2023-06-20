package com.koala.factory.extra.kugou;

import com.koala.data.models.kugou.AlbumMusicInfo.custom.AlbumInfoModel;
import com.koala.data.models.kugou.AlbumMusicInfo.custom.AudioInfoModel;
import com.koala.data.models.kugou.AlbumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
import com.koala.data.models.kugou.AlbumMusicInfo.pattern.KugouAlbumMusicItemPatternInfoDataModel;
import org.springframework.beans.BeanUtils;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 22:16
 * @description
 */
public class KugouMusicInfoDataGenerator {
    public static KugouAlbumCustomMusicInfoModel generateMusicInfoData(KugouAlbumMusicItemPatternInfoDataModel data) {
        KugouAlbumCustomMusicInfoModel musicInfoData = new KugouAlbumCustomMusicInfoModel();
        BeanUtils.copyProperties(data, musicInfoData);
        AlbumInfoModel albumInfo = new AlbumInfoModel();
        BeanUtils.copyProperties(data.getAlbumInfo(), albumInfo);
        AudioInfoModel audioInfo = new AudioInfoModel();
        BeanUtils.copyProperties(data.getAudioInfo(), audioInfo);
        musicInfoData.setAlbumInfo(albumInfo);
        musicInfoData.setAudioInfo(audioInfo);
        return musicInfoData;
    }
}
