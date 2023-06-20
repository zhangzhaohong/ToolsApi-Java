package com.koala.factory.product;

import com.koala.data.models.kugou.AlbumInfo.KugouAlbumInfoRespDataModel;
import com.koala.data.models.kugou.AlbumMusicInfo.KugouAlbumMusicInfoRespDataModel;
import com.koala.data.models.kugou.AlbumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
import com.koala.data.models.kugou.AlbumMusicInfo.pattern.KugouAlbumMusicItemPatternInfoDataModel;
import com.koala.data.models.kugou.KugouMusicDataRespModel;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HeaderUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.service.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.koala.factory.extra.kugou.KugouMusicInfoDataGenerator.generateMusicInfoData;
import static com.koala.factory.path.KugouWebPathCollector.KUGOU_ALBUM_DETAIL_SERVER_URL;
import static com.koala.factory.path.KugouWebPathCollector.KUGOU_ALBUM_MUSIC_DETAIL_SERVER_URL;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:10
 * @description
 */
public class KugouApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(KugouApiProduct.class);
    private final static Long EXPIRE_TIME = 12 * 60 * 60L;
    private final static Long DIRECT_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private String hash;
    private String albumId;
    private Integer version = 4;
    private String url;
    private String host;
    private RedisService redisService;
    private Map<String, Object> customParams;
    private KugouAlbumInfoRespDataModel<?> albumInfoData = null;
    private KugouAlbumMusicInfoRespDataModel<?> albumMusicInfoData = null;
    private KugouAlbumCustomMusicInfoModel musicInfoData = null;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setVersion(Integer version) {
        this.version = version;
        logger.info("[KugouApiProduct]({}) Switch to version: {}", this.hash, this.version);
    }

    public void setRedis(RedisService redisService) {
        this.redisService = redisService;
    }

    public void setHashAndAlbumId(String hash, String albumId) {
        this.hash = hash;
        this.albumId = albumId;
    }

    public void prepareItemIdByShareUrl() throws IOException, URISyntaxException {
        if (StringUtils.hasLength(this.url)) {
            String redirectLocation = HttpClientUtil.doGetRedirectLocation(this.url);
            String response = HttpClientUtil.doGet(redirectLocation, HeaderUtil.getKugouPublicHeader(null, null), null);
            if (StringUtils.hasLength(response)) {
                this.hash = PatternUtil.matchData("\"hash\":\"(.*?)\"", response);
                this.albumId = PatternUtil.matchData("\"album_id\":\"(.*?)\"", response);
            }
            logger.info("[KugouApiProduct]({}) prepare item id finished: {hash={}, albumId={}}", this.hash, this.hash, this.albumId);
        }
    }

    public void getAlbumInfo() throws IOException, URISyntaxException {
        if (checkNotNullHashAndAlbumId()) {
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("data", getAlbumRequestPayload());
        String response = HttpClientUtil.doGet(KUGOU_ALBUM_DETAIL_SERVER_URL, HeaderUtil.getKugouPublicHeader(null, this.customParams.get("kg_mid_cookie").toString()), params);
        logger.info("[KugouApiProduct]({}) album info: {}", this.hash, response);
        try {
            this.albumInfoData = GsonUtil.toBean(response, KugouAlbumInfoRespDataModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getAlbumMusicInfo() throws IOException, URISyntaxException {
        if (checkNotNullHashAndAlbumId()) {
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("data", getAlbumRequestPayload());
        String response = HttpClientUtil.doGet(KUGOU_ALBUM_MUSIC_DETAIL_SERVER_URL, HeaderUtil.getKugouPublicHeader(null, this.customParams.get("kg_mid_cookie").toString()), params);
        logger.info("[KugouApiProduct]({}) album music info: {}", this.hash, response);
        try {
            this.albumMusicInfoData = GsonUtil.toBean(response, KugouAlbumMusicInfoRespDataModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generatePlayInfo() {
        if (checkNotNullHashAndAlbumId() || Objects.isNull(this.albumMusicInfoData)) {
            return;
        }
        try {
            ArrayList<?> tmp1 = (ArrayList<?>) this.albumMusicInfoData.getData();
            if (tmp1.isEmpty())
                return;
            ArrayList<?> tmp2 = (ArrayList<?>) tmp1.get(0);
            if (tmp2.isEmpty())
                return;
            KugouAlbumMusicItemPatternInfoDataModel tmp = GsonUtil.toBean(GsonUtil.toString(tmp2.get(0)), KugouAlbumMusicItemPatternInfoDataModel.class);
            this.musicInfoData = generateMusicInfoData(tmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public KugouMusicDataRespModel generateItemInfoRespData() {
        KugouMusicDataRespModel respData = new KugouMusicDataRespModel(this.albumInfoData, this.albumMusicInfoData, this.musicInfoData);
        try {
            if ("1".equals(version.toString())) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respData;
    }

    private String getAlbumRequestPayload() {
        return "[{\"hash\":\"" + this.hash + "\",\"audio_id\":0,\"album_audio_id\":0}]";
    }

    private Boolean checkNotNullHashAndAlbumId() {
        return !StringUtils.hasLength(this.hash) && !StringUtils.hasLength(this.albumId);
    }

    public void setCustomParams(Map<String, Object> kugouCustomParams) {
        this.customParams = kugouCustomParams;
    }
}
