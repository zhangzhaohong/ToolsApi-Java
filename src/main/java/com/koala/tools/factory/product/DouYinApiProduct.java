package com.koala.tools.factory.product;

import com.koala.tools.enums.DouYinTypeEnums;
import com.koala.tools.models.douyin.v1.PublicTiktokDataRespModel;
import com.koala.tools.models.douyin.v1.itemInfo.ImageItemDataModel;
import com.koala.tools.models.douyin.v1.itemInfo.ItemInfoRespModel;
import com.koala.tools.models.douyin.v1.musicInfo.MusicInfoRespModel;
import com.koala.tools.models.douyin.v1.roomInfo.RoomInfoRespModel;
import com.koala.tools.models.douyin.v1.roomInfoData.RoomInfoDataRespModel;
import com.koala.tools.models.shortUrl.ShortDouYinItemDataModel;
import com.koala.tools.models.shortUrl.ShortImageDataModel;
import com.koala.tools.models.xbogus.XbogusDataModel;
import com.koala.tools.redis.service.RedisService;
import com.koala.tools.utils.*;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static com.koala.tools.enums.DouYinTypeEnums.LIVE_TYPE_1;
import static com.koala.tools.redis.RedisKeyPrefix.TIKTOK_DATA_KEY_PREFIX;
import static com.koala.tools.redis.RedisKeyPrefix.TIKTOK_DIRECT_KEY_PREFIX;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 12:34
 * @description
 */
public class DouYinApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(DouYinApiProduct.class);
    private final static Long EXPIRE_TIME = 12 * 60 * 60L;
    private final static Long DIRECT_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private final static String WEB_FROM = "web_code_link";
    private final static String AWEME_HOTSOON_APP = "aweme_hotsoon";
    private static final String TICKET_REGISTER_BODY = "{\"region\":\"cn\",\"aid\":1768,\"needFid\":false,\"service\":\"www.ixigua.com\",\"migrate_info\":{\"ticket\":\"\",\"source\":\"node\"},\"cbUrlProtocol\":\"https\",\"union\":true}";
    private static final Integer MAX_RETRY_TIMES = 10;
    private Integer version = 4;
    private String url;
    private String host;
    private String directUrl;
    private String id;
    private Integer itemTypeId = -1;
    private String itemId;
    private ItemInfoRespModel itemInfo = null;
    private MusicInfoRespModel musicItemInfo = null;
    private RoomInfoDataRespModel roomInfoData = null;
    private RedisService redisService;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void getIdByUrl() {
        if (!Objects.isNull(this.url)) {
            this.id = PatternUtil.matchData("douyin.com/(.*?)/", this.url);
        }
    }

    public void getItemIdByDirectUrl() throws IOException, URISyntaxException {
        if (!Objects.isNull(this.directUrl)) {
            DouYinTypeEnums douYinTypeEnum = DouYinTypeEnums.getEnumsByCode(this.itemTypeId);
            switch (Objects.requireNonNull(douYinTypeEnum)) {
                case MUSIC_TYPE ->
                        this.itemId = PatternUtil.matchData(douYinTypeEnum.getPrefix() + "(.*?)\\?", this.directUrl);
                case NOTE_TYPE, VIDEO_TYPE -> {
                    if (this.directUrl.startsWith("https://www.iesdouyin.com")) {
                        this.itemId = PatternUtil.matchData(douYinTypeEnum.getPrefix() + "(.*?)/", this.directUrl);
                    } else if (this.directUrl.startsWith("https://www.douyin.com")) {
                        this.itemId = PatternUtil.matchData(douYinTypeEnum.getPrefix() + "(.*?)\\?", this.directUrl);
                    } else {
                        logger.info("[DouYinApiProduct]({}, {}) getItemIdError, directUrl: {}", id, itemId, this.directUrl);
                    }
                }
                case LIVE_TYPE_1 ->
                        this.itemId = this.directUrl.replaceFirst("https://" + douYinTypeEnum.getPrefix() + "/", "");
                case LIVE_TYPE_2 -> {
                    String roomId = PatternUtil.matchData(douYinTypeEnum.getPrefix() + "(.*?)\\?", this.directUrl);
                    String liveInfoPath = "https://webcast.amemv.com/webcast/room/reflow/info/?live_id=1&room_id=" + roomId + "&app_id=1128";
                    String roomInfoResponse = doGetXbogusRequest(liveInfoPath);
                    logger.info("[DouYinApiProduct]({}, {}) roomInfoResponse: {}", id, itemId, roomInfoResponse);
                    try {
                        RoomInfoRespModel roomInfo = GsonUtil.toBean(roomInfoResponse, RoomInfoRespModel.class);
                        this.itemId = roomInfo.getData().getRoom().getOwner().getWebRid();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case IMAGE_TYPE, default ->
                        logger.info("[DouYinApiProduct]({}, {}) Unsupported item type id: {}, current url: {}", id, itemId, itemTypeId, this.directUrl);
            }
        }
    }

    public void getItemTypeByDirectUrl() {
        if (!Objects.isNull(this.directUrl)) {
            Arrays.stream(DouYinTypeEnums.values()).forEach(typeEnum -> {
                if (!Objects.isNull(typeEnum.getPrefix()) && this.directUrl.contains(typeEnum.getPrefix())) {
                    this.itemTypeId = typeEnum.getCode();
                }
            });
        }
    }

    public void getRedirectUrl() throws IOException, URISyntaxException {
        if (!Objects.isNull(this.url)) {
            String key = TIKTOK_DIRECT_KEY_PREFIX + ShortKeyGenerator.getKey(this.url);
            String tmp = redisService.get(key);
            if (StringUtils.hasLength(tmp)) {
                this.directUrl = tmp;
                logger.info("[DouYinApiProduct]({}, {}) get direct url from redis, directUrl: {}", id, itemId, this.directUrl);
                return;
            }
            if (this.url.contains(LIVE_TYPE_1.getPrefix())) {
                this.directUrl = this.url;
            } else {
                this.directUrl = HttpClientUtil.doGetRedirectLocation(this.url, null, HeaderUtil.getDouYinDownloadHeader());
                if (Objects.equals(WEB_FROM, HttpClientUtil.getParam(this.directUrl, "from")) || Objects.equals(AWEME_HOTSOON_APP, HttpClientUtil.getParam(this.directUrl, "app"))) {
                    String ticket = getTicket();
                    String webDirectUrl = HttpClientUtil.doGetRedirectLocation(this.directUrl, HeaderUtil.getDouYinWebRequestSpecialHeader(ticket), HeaderUtil.getDouYinDownloadHeader());
                    if (StringUtils.hasLength(webDirectUrl)) {
                        this.directUrl = webDirectUrl;
                    }
                }
            }
            if (StringUtils.hasLength(this.directUrl)) {
                redisService.set(key, this.directUrl, DIRECT_EXPIRE_TIME);
            }
            logger.info("[DouYinApiProduct]({}, {}) get direct url success, directUrl: {}", id, itemId, this.directUrl);
        }
    }

    public void getItemInfoData() throws IOException, URISyntaxException {
        if (!Objects.isNull(this.itemId)) {
            switch (Objects.requireNonNull(DouYinTypeEnums.getEnumsByCode(this.itemTypeId))) {
                case MUSIC_TYPE -> {
                    int count = 1;
                    int cursor = 0;
                    String musicInfoPath = "https://aweme.snssdk.com/aweme/v1/web/music/aweme/?music_id=" + this.itemId + "&cursor=" + cursor + "&count=" + count + "&device_platform=webapp&aid=6383";
                    logger.info("[DouYinApiProduct]({}, {}) musicInfoPath: {}", id, itemId, musicInfoPath);
                    String musicInfoDataResponse = doGetXbogusRequest(musicInfoPath);
                    logger.info("[DouYinApiProduct]({}, {}) itemInfoResponse: {}", id, itemId, musicInfoDataResponse);
                    try {
                        this.musicItemInfo = GsonUtil.toBean(musicInfoDataResponse, MusicInfoRespModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case VIDEO_TYPE, NOTE_TYPE -> {
                    String itemInfoPath = "https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id=" + this.itemId + "&device_platform=webapp&aid=6383";
                    logger.info("[DouYinApiProduct]({}, {}) itemInfoPath: {}", id, itemId, itemInfoPath);
                    String itemInfoResponse = doGetXbogusRequest(itemInfoPath);
                    logger.info("[DouYinApiProduct]({}, {}) itemInfoResponse: {}", id, itemId, itemInfoResponse);
                    try {
                        this.itemInfo = GsonUtil.toBean(itemInfoResponse, ItemInfoRespModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case LIVE_TYPE_1, LIVE_TYPE_2 -> {
                    String liveInfoDataPath = "https://live.douyin.com/webcast/room/web/enter/?aid=6383&device_platform=web&web_rid=" + this.itemId;
                    logger.info("[DouYinApiProduct]({}, {}) liveInfoDataPath: {}", id, this.itemId, liveInfoDataPath);
                    String liveInfoDataResponse = doGetXbogusRequest(liveInfoDataPath);
                    logger.info("[DouYinApiProduct]({}, {}) liveInfoDataResponse: {}", id, this.itemId, liveInfoDataResponse);
                    try {
                        this.roomInfoData = GsonUtil.toBean(liveInfoDataResponse, RoomInfoDataRespModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case IMAGE_TYPE, default ->
                        logger.info("[DouYinApiProduct]({}, {}) Unsupported item type id: {}", id, itemId, itemTypeId);
            }
        }
    }

    /**
     * 下面是打印log区域
     */
    public void printParams() {
        logger.info("[DouYinApiProduct]({}, {}) params: {url={}, directPath={}, itemTypeId={}}", id, itemId, url, directUrl, itemTypeId);
    }

    public PublicTiktokDataRespModel generateData() {
        PublicTiktokDataRespModel publicData = null;
        try {
            switch (Objects.requireNonNull(DouYinTypeEnums.getEnumsByCode(this.itemTypeId))) {
                case MUSIC_TYPE -> {
                    if (!Objects.isNull(this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic())) {
                        if (this.version.equals(4)) {
                            String key = ShortKeyGenerator.getKey(null);
                            String title = this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().getTitle();
                            String link = this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().getPlayUrl().getUri();
                            redisService.set(TIKTOK_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortDouYinItemDataModel(title, link)), EXPIRE_TIME);
                            this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().setMockPreviewMusicPath(host + "tools/DouYin/pro/player/music/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)));
                            this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().setMockDownloadMusicPath(ShortKeyGenerator.generateShortUrl(host + "tools/DouYin/download/music?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)), EXPIRE_TIME, host, redisService).getUrl());
                        } else if (this.version.equals(3)) {
                            String title = this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().getTitle();
                            String link = this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().getPlayUrl().getUri();
                            this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().setMockPreviewMusicPath(host + "tools/DouYin/pro/player/music?" + (StringUtils.hasLength(title) ? "title=" + Base64Utils.encodeToUrlSafeString(title.getBytes(StandardCharsets.UTF_8)) + "&" : "") + "path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)));
                            this.musicItemInfo.getAwemeMusicDetail().get(0).getMusic().setMockDownloadMusicPath(host + "tools/DouYin/download/music?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)));
                        }
                    }
                }
                case NOTE_TYPE -> {
                    if (!Objects.isNull(this.itemInfo.getAwemeDetailModel().getImages())) {
                        if (this.version.equals(4)) {
                            String key = ShortKeyGenerator.getKey(null);
                            String title = this.itemInfo.getAwemeDetailModel().getDesc();
                            ArrayList<String> urlList = new ArrayList<>();
                            for (Object image : GsonUtil.toBean(GsonUtil.toString(this.itemInfo.getAwemeDetailModel().getImages()), ArrayList.class)) {
                                ImageItemDataModel imageItem = GsonUtil.toBean(GsonUtil.toString(image), ImageItemDataModel.class);
                                if (!Objects.isNull(imageItem.getUrlList())) {
                                    urlList.add(imageItem.getUrlList().get(0));
                                }
                            }
                            redisService.set(TIKTOK_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortImageDataModel(title, urlList)), EXPIRE_TIME);
                            this.itemInfo.getAwemeDetailModel().setMockPreviewPicturePath(host + "tools/DouYin/pro/player/picture/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)));
                        }
                    }
                }
                case LIVE_TYPE_1, LIVE_TYPE_2 -> {
                    if (!Objects.isNull(this.roomInfoData.getData().getData().get(0).getStreamUrl())) {
                        if (this.version.equals(4)) {
                            String key = ShortKeyGenerator.getKey(null);
                            String title = this.roomInfoData.getData().getData().get(0).getOwner().getNickname() + "的直播间";
                            String link = this.roomInfoData.getData().getData().get(0).getStreamUrl().getFlvPullUrl().getFullHd1().replaceFirst("http://", "https://");
                            redisService.set(TIKTOK_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortDouYinItemDataModel(title, link)), EXPIRE_TIME);
                            this.roomInfoData.getData().getData().get(0).getStreamUrl().setMockPreviewLivePath(host + "tools/DouYin/pro/player/live/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)));
                        } else if (this.version.equals(3)) {
                            String title = this.roomInfoData.getData().getData().get(0).getOwner().getNickname() + "的直播间";
                            String link = this.roomInfoData.getData().getData().get(0).getStreamUrl().getFlvPullUrl().getFullHd1().replaceFirst("http://", "https://");
                            this.roomInfoData.getData().getData().get(0).getStreamUrl().setMockPreviewLivePath(host + "tools/DouYin/pro/player/live?" + (StringUtils.hasLength(title) && !"的直播间".equals(title) ? "title=" + Base64Utils.encodeToUrlSafeString(title.getBytes(StandardCharsets.UTF_8)) + "&" : "") + "path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)));
                        } else if (this.version.equals(2)) {
                            String link = this.roomInfoData.getData().getData().get(0).getStreamUrl().getFlvPullUrl().getFullHd1().replaceFirst("http://", "https://");
                            this.roomInfoData.getData().getData().get(0).getStreamUrl().setMockPreviewLivePath(host + "tools/DouYin/preview/liveStream?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)));
                        }
                    }
                }
                case VIDEO_TYPE -> {
                    String vid = this.itemInfo.getAwemeDetailModel().getVideo().getPlayAddrInfoModel().getUri();
                    String ratio = this.itemInfo.getAwemeDetailModel().getVideo().getRatio();
                    if (ObjectUtils.isEmpty(ratio) || Objects.equals(ratio, "default")) {
                        ratio = "540p";
                    }
                    if (!ObjectUtils.isEmpty(vid)) {
                        if (this.version.equals(4)) {
                            String key = ShortKeyGenerator.getKey(null);
                            String title = this.itemInfo.getAwemeDetailModel().getDesc();
                            String link = this.itemInfo.getAwemeDetailModel().getVideo().getPlayAddrInfoModel().getUrlList().get(0);
                            String previewPath = host + "tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8));
                            redisService.set(TIKTOK_DATA_KEY_PREFIX + key, GsonUtil.toString(new ShortDouYinItemDataModel(title, previewPath)), EXPIRE_TIME);
                            this.itemInfo.getAwemeDetailModel().getVideo().setRealPath(ShortKeyGenerator.generateShortUrl(link, EXPIRE_TIME, host, redisService).getUrl());
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockPreviewVidPath(host + "tools/DouYin/pro/player/video/short?key=" + Base64Utils.encodeToUrlSafeString(key.getBytes(StandardCharsets.UTF_8)));
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockDownloadVidPath(ShortKeyGenerator.generateShortUrl(host + "tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)) + "&isDownload=true", EXPIRE_TIME, host, redisService).getUrl());
                        } else if (this.version.equals(3)) {
                            String title = this.itemInfo.getAwemeDetailModel().getDesc();
                            String link = this.itemInfo.getAwemeDetailModel().getVideo().getPlayAddrInfoModel().getUrlList().get(0);
                            String previewPath = host + "tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8));
                            this.itemInfo.getAwemeDetailModel().getVideo().setRealPath(link);
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockPreviewVidPath(host + "tools/DouYin/pro/player/video?" + (StringUtils.hasLength(title) ? "title=" + Base64Utils.encodeToUrlSafeString(title.getBytes(StandardCharsets.UTF_8)) + "&" : "") + "path=" + Base64Utils.encodeToUrlSafeString(previewPath.getBytes(StandardCharsets.UTF_8)));
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockDownloadVidPath(host + "tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)) + "&isDownload=true");
                        } else if (this.version.equals(2)) {
                            String link = this.itemInfo.getAwemeDetailModel().getVideo().getPlayAddrInfoModel().getUrlList().get(0);
                            this.itemInfo.getAwemeDetailModel().getVideo().setRealPath(link);
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockPreviewVidPath(host + "tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)));
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockDownloadVidPath(host + "tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(link.getBytes(StandardCharsets.UTF_8)) + "&isDownload=true");
                        } else if (this.version.equals(1)) {
                            String link = "https://aweme.snssdk.com/aweme/v1/play/?video_id=" + vid + "&line=0&ratio=" + ratio + "&media_type=4&vr_type=0&improve_bitrate=0&is_play_url=1&is_support_h265=0&source=PackSourceEnum_PUBLISH";
                            this.itemInfo.getAwemeDetailModel().getVideo().setRealPath(link);
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockPreviewVidPath(host + "tools/DouYin/player/video?vid=" + vid + "&ratio=" + ratio + "&isDownload=0");
                            this.itemInfo.getAwemeDetailModel().getVideo().setMockDownloadVidPath(host + "tools/DouYin/player/video?vid=" + vid + "&ratio=" + ratio + "&isDownload=1");
                        }
                        // logger.info("[DouYinApiProduct]({}, {}) realFile: {}", id, itemId,HttpClientUtil.doGetRedirectLocation(link, HeaderUtil.getDouYinDownloadHeader(), null));
                    }
                }
                case IMAGE_TYPE, default ->
                        logger.info("[DouYinApiProduct]({}, {}) Unsupported item type id: {}", id, itemId, itemTypeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        publicData = new PublicTiktokDataRespModel(this.itemTypeId, this.itemInfo, this.musicItemInfo, this.roomInfoData);
        logger.info("[DouYinApiProduct]({}, {}) publicData: {}", id, itemId, publicData);
        return publicData;
    }

    private String doGetXbogusRequest(String inputUrl) throws IOException, URISyntaxException {
        XbogusDataModel xbogusDataModel = XbogusUtil.encrypt(inputUrl);
        if (Objects.isNull(xbogusDataModel) || ObjectUtils.isEmpty(xbogusDataModel.getUrl())) {
            logger.error("[DouYinApiProduct]({}, {}) encrypt error, encryptResult: {}", id, itemId, xbogusDataModel);
            throw new NullPointerException("encrypt error");
        }
        logger.info("[DouYinApiProduct]({}, {}) encryptResult: {}", id, itemId, xbogusDataModel);
        int retryTime = 0;
        String response;
        while (retryTime < MAX_RETRY_TIMES) {
            response = HttpClientUtil.doGet(xbogusDataModel.getUrl(), HeaderUtil.getDouYinSpecialHeader(xbogusDataModel.getMstoken(), xbogusDataModel.getTtwid()), null);
            if (StringUtils.hasLength(response)) {
                return response;
            }
            retryTime++;
            logger.info("[DouYinApiProduct]({}, {}) Get data error, retry time: {}", id, itemId, retryTime);
        }
        throw new NullPointerException();
    }

    public void setVersion(Integer version) {
        this.version = version;
        logger.info("[DouYinApiProduct]({}, {}) setting version success: {}", id, itemId, version);
    }

    public void setRedis(RedisService redisService) {
        this.redisService = redisService;
    }

    private String getTicket() throws IOException {
        AtomicReference<String> ticket = new AtomicReference<>(null);
        List<Cookie> cookieData = HttpClientUtil.doPostJsonAndReturnCookie("https://ttwid.bytedance.com/ttwid/union/register/", HeaderUtil.getDouYinTicketGeneratorHeader(), TICKET_REGISTER_BODY);
        Optional<Cookie> ticketData = cookieData.stream().filter(item -> "ttwid".equals(item.getName())).findFirst();
        ticketData.ifPresent(cookie -> ticket.set(cookie.getValue()));
        return ticket.get();
    }
}
