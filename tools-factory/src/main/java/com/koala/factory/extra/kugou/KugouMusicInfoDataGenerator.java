package com.koala.factory.extra.kugou;

import com.koala.base.enums.KugouRequestQualityEnums;
import com.koala.data.models.kugou.albumMusicInfo.custom.AlbumInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.AudioInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.PlayInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.pattern.KugouAlbumMusicItemPatternInfoDataModel;
import com.koala.service.utils.GsonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
        musicInfoData.setAlbumInfo(albumInfo);
        AudioInfoModel audioInfo = new AudioInfoModel();
        BeanUtils.copyProperties(data.getAudioInfo(), audioInfo);
        Map<String, Object> musicPlayInfoMapData = GsonUtil.toMaps(GsonUtil.toString(data.getAudioInfo()));
        HashMap<String, PlayInfoModel> playInfoData = new HashMap<>();
        Arrays.stream(KugouRequestQualityEnums.values()).forEach(qualityEnum -> {
            playInfoData.put(qualityEnum.getType(), new PlayInfoModel(
                    getDataFromMap(qualityEnum.getBitrateKey(), musicPlayInfoMapData),
                    getDataFromMap(qualityEnum.getHashKey(), musicPlayInfoMapData),
                    getDataFromMap(qualityEnum.getFilesizeKey(), musicPlayInfoMapData),
                    getDataFromMap(qualityEnum.getTimelengthKey(), musicPlayInfoMapData)
            ));
        });
        audioInfo.setPlayInfoList(playInfoData);
        musicInfoData.setAudioInfo(audioInfo);
        return musicInfoData;
    }

    private static String getDataFromMap(String key, Map<String, Object> data) {
        if (StringUtils.hasLength(key)) {
            return data.get(key).toString();
        }
        return null;
    }
}
