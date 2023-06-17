package com.koala.factory.product;

import com.koala.base.enums.NeteaseRequestQualityEnums;
import com.koala.data.models.netease.NeteaseMusicDataRespModel;
import com.koala.data.models.netease.detailInfo.NeteaseMusicItemDetailInfoRespModel;
import com.koala.data.models.netease.itemInfo.NeteaseMusicItemInfoRespModel;
import com.koala.data.models.netease.lyricInfo.NeteaseMusicLyricInfoRespModel;
import com.koala.data.models.shortUrl.ShortNeteaseItemDataModel;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static com.koala.factory.path.NeteaseWebPathCollector.*;
import static com.koala.service.data.redis.RedisKeyPrefix.*;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 13:53
 * @description
 */
public class NeteaseApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(NeteaseApiProduct.class);
    private final static Long EXPIRE_TIME = 12 * 60 * 60L;
    private final static Long DETAIL_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private final static Long LYRIC_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private static final String UNKNOWN_ARTIST = "未知艺术家";
    private static final Random RANDOM = new Random(0);
    private static final String REQUEST_ID = String.valueOf(RANDOM.nextLong(20000000, 30000000));
    private static final String DEVICE_ID = UUID.randomUUID().toString().replace("-", "");
    private static final byte[] AES_KEY = "e82ckenh8dichen8".getBytes(StandardCharsets.UTF_8);
    private String host;
    private String cookie;
    private Integer version = 1;
    private String url;
    private String musicId;
    private String servicePath;
    private String level = NeteaseRequestQualityEnums.DEFAULT.getType();
    private String params;
    private String detailPayload;
    private NeteaseMusicItemInfoRespModel itemInfoData = null;
    private NeteaseMusicItemDetailInfoRespModel itemDetailInfoData = null;
    private NeteaseMusicLyricInfoRespModel itemLyricInfoData = null;
    private RedisService redisService;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setRedis(RedisService redisService) {
        this.redisService = redisService;
    }

    public void setLevel(String level) {
        if (StringUtils.hasLength(level)) {
            this.level = level;
        }
    }

    public void setVersion(Integer version) {
        this.version = version;
        logger.info("[NeteaseApiProject]({}) Switch to version: {}", this.musicId, this.version);
    }

    public void getIdByUrl() {
        if (StringUtils.hasLength(this.url)) {
            String id = PatternUtil.matchData("id=(\\d+)", this.url);
            if (StringUtils.hasLength(id)) {
                this.musicId = id.replace("id=", "");
                logger.info("[NeteaseApiProject]({}) Get music id success, url: {}", this.musicId, this.url);
            }
        }
    }

    public void initRequest() {
        if (StringUtils.hasLength(this.url)) {
            String neteaseServiceHostName = PatternUtil.matchFullData("http(s)?://(([\\w-]+\\.)+\\w+(:\\d{1,5})?)", NETEASE_SERVER_URL);
            if (StringUtils.hasLength(neteaseServiceHostName)) {
                this.servicePath = NETEASE_SERVER_URL.replaceFirst(neteaseServiceHostName, "").replaceFirst("/eapi/", "/api/");
                String digest = MD5Utils.customMd5(getDigestPayload(this.level));
                this.params = AESUtils.toHexString(Optional.ofNullable(AESUtils.aes256Encode("%s-36cd479b6b5-%s-36cd479b6b5-%s".formatted(this.servicePath, getPayload(this.level), digest), AES_KEY)).orElse(new byte[]{}));
                logger.info("[NeteaseApiProject]({}) params: {}", this.musicId, "%s-36cd479b6b5-%s-36cd479b6b5-%s".formatted(this.servicePath, getPayload(this.level), digest));
                this.detailPayload = getDetailPayload();
                logger.info("[NeteaseApiProject]({}) init finished, data: [digest: {}, params: {}, detail payload: {}], url: {}", this.musicId, digest, this.params, this.detailPayload, this.url);
            } else {
                logger.error("[NeteaseApiProject]({}) Get host error, url: {}", this.musicId, this.url);
            }
        }
    }

    public void getItemInfoData() throws IOException {
        if (StringUtils.hasLength(this.url)) {
            HashMap<String, String> data = new HashMap<>();
            data.put("params", this.params);
            String itemInfoResponse = HttpClientUtil.doPost(NETEASE_SERVER_URL, HeaderUtil.getNeteaseHeader(cookie), data);
            logger.info("[NeteaseApiProject]({}) itemInfoResponse: {}", this.musicId, itemInfoResponse);
            try {
                this.itemInfoData = GsonUtil.toBean(itemInfoResponse, NeteaseMusicItemInfoRespModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getItemDetailData() throws IOException {
        if (StringUtils.hasLength(this.url)) {
            String key = NETEASE_DETAIL_DATA_KEY_PREFIX + ShortKeyGenerator.getKey(this.url);
            String tmp = redisService.get(key);
            if (StringUtils.hasLength(tmp)) {
                this.itemDetailInfoData = GsonUtil.toBean(tmp, NeteaseMusicItemDetailInfoRespModel.class);
                logger.info("[NeteaseApiProject]({}) get detail info from redis, info: {}", this.musicId, tmp);
                if (!this.itemDetailInfoData.getSongs().isEmpty()) {
                    return;
                }
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("c", this.detailPayload);
            String itemDetailInfoResponse = HttpClientUtil.doPost(NETEASE_DETAIL_SERVER_URL, HeaderUtil.getNeteaseDetailHeader(), data);
            logger.info("[NeteaseApiProject]({}) itemDetailInfoResponse: {}", this.musicId, itemDetailInfoResponse);
            try {
                this.itemDetailInfoData = GsonUtil.toBean(itemDetailInfoResponse, NeteaseMusicItemDetailInfoRespModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (StringUtils.hasLength(itemDetailInfoResponse)) {
                redisService.set(key, itemDetailInfoResponse, DETAIL_EXPIRE_TIME);
            }
        }
    }

    public void getItemLyricData() throws IOException, URISyntaxException {
        if (StringUtils.hasLength(this.url)) {
            String key = NETEASE_LYRIC_DATA_KEY_PREFIX + ShortKeyGenerator.getKey(this.url);
            String tmp = redisService.get(key);
            if (StringUtils.hasLength(tmp)) {
                this.itemLyricInfoData = GsonUtil.toBean(tmp, NeteaseMusicLyricInfoRespModel.class);
                logger.info("[NeteaseApiProject]({}) get lyric info from redis, info: {}", this.musicId, tmp);
                if (!Objects.isNull(this.itemLyricInfoData)) {
                    return;
                }
            }
            HashMap<String, String> data = new HashMap<>();
            data.put("os", "pc");
            data.put("id", this.musicId);
            data.put("lv", "-1");
            data.put("kv", "-1");
            data.put("tv", "-1");
            String itemLyricInfoResponse = HttpClientUtil.doGet(NETEASE_LYRIC_SERVER_URL, HeaderUtil.getNeteaseDetailHeader(), data);
            logger.info("[NeteaseApiProject]({}) itemLyricInfoResponse: {}", this.musicId, itemLyricInfoResponse);
            try {
                this.itemLyricInfoData = GsonUtil.toBean(itemLyricInfoResponse, NeteaseMusicLyricInfoRespModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (StringUtils.hasLength(itemLyricInfoResponse)) {
                redisService.set(key, itemLyricInfoResponse, LYRIC_EXPIRE_TIME);
            }
        }
    }

    public NeteaseMusicDataRespModel generateItemInfoRespData() {
        NeteaseMusicDataRespModel respData = new NeteaseMusicDataRespModel(this.itemInfoData, this.itemDetailInfoData, this.itemLyricInfoData);
        try {
            if (!Objects.isNull(this.itemInfoData) && !Objects.isNull(this.itemDetailInfoData) && !respData.getItemInfo().getData().isEmpty()) {
                String artist = UNKNOWN_ARTIST;
                if (version == 1) {
                    String key = ShortKeyGenerator.getKey(null);
                    String title = respData.getDetailInfo().getSongs().get(0).getName();
                    String origin = respData.getItemInfo().getData().get(0).getUrl().split("\\?")[0];
                    String link = ShortKeyGenerator.generateShortUrl(origin, EXPIRE_TIME, host, redisService).getUrl();
                    String type = respData.getItemInfo().getData().get(0).getType();
                    StringBuilder artistBuilder = new StringBuilder();
                    if (!respData.getDetailInfo().getSongs().isEmpty() && !respData.getDetailInfo().getSongs().get(0).getAr().isEmpty()) {
                        AtomicInteger index = new AtomicInteger();
                        respData.getDetailInfo().getSongs().get(0).getAr().forEach(item -> {
                            index.getAndIncrement();
                            artistBuilder.append(item.getName());
                            if (index.get() < respData.getDetailInfo().getSongs().get(0).getAr().size()) {
                                artistBuilder.append(",");
                            }
                        });
                        if (StringUtils.hasLength(artistBuilder.toString())) {
                            artist = artistBuilder.toString();
                        }
                    }
                    redisService.set(NETEASE_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortNeteaseItemDataModel(title, link, origin, type, artist)), EXPIRE_TIME);
                    respData.getItemInfo().getData().get(0).setMockPreviewPath(host + "tools/Netease/pro/player/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)));
                    respData.getItemInfo().getData().get(0).setMockDownloadPath(host + "tools/Netease/download/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respData;
    }

    private String getPayload(String level) {
        return "{\"ids\": [\"" + this.musicId + "\"], \"level\": \"" + level + "\", \"encodeType\": \"flac\", \"header\": \"{\\\"os\\\": \\\"pc\\\", \\\"appver\\\": \\\"\\\", \\\"osver\\\": \\\"\\\", \\\"deviceId\\\": \\\"" + DEVICE_ID + "\\\", \\\"requestId\\\": \\\"" + REQUEST_ID + "\\\"}\"}";
    }

    private String getDigestPayload(String level) {
        return "nobody" + this.servicePath + "use" + getPayload(level) + "md5forencrypt";
    }

    private String getDetailPayload() {
        return "[{\"id\": " + this.musicId + ", \"v\": 0}]";
    }

}
