package com.koala.factory.product;

import com.koala.base.enums.KugouRequestQualityEnums;
import com.koala.data.models.kugou.albumInfo.KugouAlbumInfoRespDataModel;
import com.koala.data.models.kugou.albumMusicInfo.KugouAlbumMusicInfoRespDataModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.AlbumInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.AudioInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.KugouAlbumCustomMusicInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.custom.PlayInfoModel;
import com.koala.data.models.kugou.albumMusicInfo.pattern.KugouAlbumMusicItemPatternInfoDataModel;
import com.koala.data.models.kugou.KugouMusicDataRespModel;
import com.koala.data.models.shortUrl.ShortKugouItemDataModel;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.koala.factory.extra.kugou.KugouMusicInfoDataGenerator.generateMusicInfoData;
import static com.koala.factory.path.KugouWebPathCollector.*;
import static com.koala.service.data.redis.RedisKeyPrefix.*;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:10
 * @description
 */
public class KugouApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(KugouApiProduct.class);
    private final static Long EXPIRE_TIME = 12 * 60 * 60L;
    private final static Long ALBUM_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private final static Long ALBUM_MUSIC_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private String title, authorName;
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
    private String lyricInfoData;

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
            String response;
            if (StringUtils.hasLength(redirectLocation)) {
                response = HttpClientUtil.doGet(redirectLocation, HeaderUtil.getKugouPublicHeader(null, null), null);
            } else {
                response = HttpClientUtil.doGet(this.url, HeaderUtil.getKugouPublicHeader(null, null), null);
            }
            if (StringUtils.hasLength(response)) {
                this.title = UnicodeUtils.unicodeToString(PatternUtil.matchData("\"song_name\":\"(.*?)\"", response));
                this.authorName = UnicodeUtils.unicodeToString(PatternUtil.matchData("\"author_name\":\"(.*?)\"", response));
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
        String key = KUGOU_ALBUM_DATA_KEY_PREFIX + ShortKeyGenerator.getKey(this.url);
        String tmp = redisService.get(key);
        if (StringUtils.hasLength(tmp)) {
            this.albumInfoData = GsonUtil.toBean(tmp, KugouAlbumInfoRespDataModel.class);
            logger.info("[KugouApiProduct]({}) get album info from redis: {}", this.hash, tmp);
            if (!Objects.isNull(this.albumInfoData)) {
                return;
            }
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("data", getAlbumRequestPayload());
        String response = HttpClientUtil.doGet(KUGOU_ALBUM_DETAIL_SERVER_URL, HeaderUtil.getKugouPublicHeader(null, this.customParams.get("kg_cookie").toString()), params);
        logger.info("[KugouApiProduct]({}) album info: {}", this.hash, response);
        try {
            this.albumInfoData = GsonUtil.toBean(response, KugouAlbumInfoRespDataModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (StringUtils.hasLength(response)) {
            redisService.set(key, response, ALBUM_EXPIRE_TIME);
        }
    }

    public void getAlbumMusicInfo() throws IOException, URISyntaxException {
        if (checkNotNullHashAndAlbumId()) {
            return;
        }
        this.albumMusicInfoData = requestAlbumMusicInfoAndToBean();
    }

    private KugouAlbumMusicInfoRespDataModel<?> requestAlbumMusicInfoAndToBean() throws IOException, URISyntaxException {
        if (checkNotNullHashAndAlbumId()) {
            return null;
        }
        KugouAlbumMusicInfoRespDataModel<?> result = null;
        String key = KUGOU_ALBUM_MUSIC_DATA_KEY_PREFIX + ShortKeyGenerator.getKey(this.hash);
        String tmp = redisService.get(key);
        if (StringUtils.hasLength(tmp)) {
            result = GsonUtil.toBean(tmp, KugouAlbumMusicInfoRespDataModel.class);
            logger.info("[KugouApiProduct]({}) get album music info from redis: {}", this.hash, tmp);
            if (!Objects.isNull(result)) {
                return result;
            }
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("data", getAlbumRequestPayload());
        String response = HttpClientUtil.doGet(KUGOU_ALBUM_MUSIC_DETAIL_SERVER_URL, HeaderUtil.getKugouPublicHeader(null, this.customParams.get("kg_cookie").toString()), params);
        logger.info("[KugouApiProduct]({}) album music info: {}", this.hash, response);
        try {
            result = GsonUtil.toBean(response, KugouAlbumMusicInfoRespDataModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (StringUtils.hasLength(response)) {
            redisService.set(key, response, ALBUM_MUSIC_EXPIRE_TIME);
        }
        return result;
    }

    public void generatePlayInfo() throws IOException, URISyntaxException {
        if (checkNotNullHashAndAlbumId()) {
            return;
        }
        KugouAlbumMusicInfoRespDataModel<?> tmpAlbumMusicInfoRespData = null;
        if (Objects.isNull(this.albumMusicInfoData)) {
            tmpAlbumMusicInfoRespData = requestAlbumMusicInfoAndToBean();
        } else {
            tmpAlbumMusicInfoRespData = this.albumMusicInfoData;
        }
        try {
            ArrayList<?> tmp1 = (ArrayList<?>) tmpAlbumMusicInfoRespData.getData();
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

    public void getLyricInfo() {
        if (checkNotNullHashAndAlbumId()) {
            return;
        }
        String key = KUGOU_LYRIC_DATA_KEY_PREFIX + ShortKeyGenerator.getKey(this.hash);
        String tmp = redisService.get(key);
        if (StringUtils.hasLength(tmp)) {
            this.lyricInfoData = tmp;
            logger.info("[KugouApiProduct]({}) get music lyric info from redis: {}", this.hash, tmp);
            if (!Objects.isNull(GsonUtil.toMaps(tmp))) {
                return;
            }
        }
        try {
            HashMap<String, String> lyricInfoListParams = new HashMap<>();
            lyricInfoListParams.put("ver", "1");
            lyricInfoListParams.put("client", "mobi");
            lyricInfoListParams.put("duration", "");
            lyricInfoListParams.put("hash", this.hash);
            lyricInfoListParams.put("album_audio_id", "");
            String lyricInfoListResponse = HttpClientUtil.doGet(KUGOU_SEARCH_LYRIC_SERVER_URL, HeaderUtil.getKugouPublicHeader(null, null), lyricInfoListParams);
            if (StringUtils.hasLength(lyricInfoListResponse)) {
                ArrayList<?> lyricInfoListTmp = (ArrayList<?>) GsonUtil.toMaps(lyricInfoListResponse).get("candidates");
                if (lyricInfoListTmp.isEmpty()) {
                    return;
                }
                Map<String, Object> candidate = GsonUtil.toMaps(GsonUtil.toString(lyricInfoListTmp.get(0)));
                String lyricId = (String) candidate.get("id");
                String lyricAccessKey = (String) candidate.get("accesskey");
                HashMap<String, String> lyricParams = new HashMap<>();
                lyricParams.put("ver", "1");
                lyricParams.put("client", "pc");
                lyricParams.put("id", lyricId);
                lyricParams.put("accesskey", lyricAccessKey);
                lyricParams.put("fmt", "lrc");
                lyricParams.put("charset", "utf8");
                String response = HttpClientUtil.doGet(KUGOU_LYRIC_DETAIL_SERVER_URL, HeaderUtil.getKugouPublicHeader(null, null), lyricParams);
                if (StringUtils.hasLength(response)) {
                    Map<String, Object> lyricData = GsonUtil.toMaps(response);
                    lyricData.put("decode_content", Base64Utils.decode(lyricData.get("content").toString()));
                    this.lyricInfoData = GsonUtil.toString(lyricData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KugouMusicDataRespModel<?> generateItemInfoRespData() {
        KugouMusicDataRespModel<?> respData = new KugouMusicDataRespModel<>(this.albumInfoData, this.albumMusicInfoData, this.musicInfoData, GsonUtil.toMaps(this.lyricInfoData), new LinkedHashMap<>(), new LinkedHashMap<>());
        try {
            if ("1".equals(version.toString())) {
                String key = ShortKeyGenerator.getKey(null);
                if (Objects.isNull(this.musicInfoData)) {
                    AlbumInfoModel patternAlbumInfo = new AlbumInfoModel();
                    patternAlbumInfo.setAlbumId(this.albumId);
                    AudioInfoModel patternAudioInfo = new AudioInfoModel();
                    LinkedHashMap<String, PlayInfoModel> patternPlayInfo = new LinkedHashMap<>();
                    patternPlayInfo.put(KugouRequestQualityEnums.QUALITY_DEFAULT.getType(), new PlayInfoModel(null, this.hash, null, null));
                    patternAudioInfo.setPlayInfoList(patternPlayInfo);
                    KugouAlbumCustomMusicInfoModel patternMusicInfo = new KugouAlbumCustomMusicInfoModel();
                    patternMusicInfo.setSongname(this.title);
                    patternMusicInfo.setAuthorName(this.authorName);
                    patternMusicInfo.setAlbumInfo(patternAlbumInfo);
                    patternMusicInfo.setAudioInfo(patternAudioInfo);
                    redisService.set(KUGOU_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortKugouItemDataModel(this.title, this.authorName, patternMusicInfo, null)), EXPIRE_TIME);
                    respData.getMockPreviewPath().put(KugouRequestQualityEnums.QUALITY_DEFAULT.getType(), host + "tools/Kugou/pro/player/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)) + "&quality=" + KugouRequestQualityEnums.QUALITY_DEFAULT.getType());
                    respData.getMockDownloadPath().put(KugouRequestQualityEnums.QUALITY_DEFAULT.getType(), host + "tools/Kugou/download/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)) + "&quality=" + KugouRequestQualityEnums.QUALITY_DEFAULT.getType());
                } else {
                    redisService.set(KUGOU_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortKugouItemDataModel(this.title, this.authorName, this.musicInfoData, null)), EXPIRE_TIME);
                    Arrays.stream(KugouRequestQualityEnums.values()).forEach(qualityEnum -> {
                        PlayInfoModel tmp = this.musicInfoData.getAudioInfo().getPlayInfoList().get(qualityEnum.getType());
                        if (StringUtils.hasLength(tmp.getHash())) {
                            respData.getMockPreviewPath().put(qualityEnum.getType(), host + "tools/Kugou/pro/player/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)) + "&quality=" + qualityEnum.getType());
                            respData.getMockDownloadPath().put(qualityEnum.getType(), host + "tools/Kugou/download/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)) + "&quality=" + qualityEnum.getType());
                        }
                    });
                }
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
