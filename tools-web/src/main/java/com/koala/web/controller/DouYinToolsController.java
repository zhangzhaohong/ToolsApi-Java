package com.koala.web.controller;

import com.koala.base.enums.DouYinRequestTypeEnums;
import com.koala.base.enums.DouYinTypeEnums;
import com.koala.data.models.douyin.v1.PublicTiktokDataRespModel;
import com.koala.data.models.douyin.v1.itemInfo.ItemInfoRespModel;
import com.koala.data.models.douyin.v1.musicInfo.MusicInfoRespModel;
import com.koala.data.models.douyin.v1.roomInfoData.RoomInfoDataRespModel;
import com.koala.factory.builder.ConcreteDouYinApiBuilder;
import com.koala.factory.builder.DouYinApiBuilder;
import com.koala.factory.director.DouYinApiManager;
import com.koala.factory.extra.tiktok.TiktokCookieUtil;
import com.koala.factory.extra.tiktok.XGorgonUtil;
import com.koala.factory.product.DouYinApiProduct;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.custom.http.annotation.MixedHttpRequest;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.Base64Utils;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HeaderUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.web.HostManager;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.koala.base.enums.DouYinResponseEnums.*;
import static com.koala.base.enums.DouYinTypeEnums.*;
import static com.koala.factory.path.TiktokPathCollector.*;
import static com.koala.service.data.redis.RedisKeyPrefix.*;
import static com.koala.service.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:33
 * @description
 */
@RestController
@RequestMapping("tools/DouYin")
public class DouYinToolsController {

    private static final Logger logger = LoggerFactory.getLogger(DouYinToolsController.class);

    private static final Integer MAX_RETRY_TIMES = 10;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    private HostManager hostManager;

    @Resource(name = "RedisService")
    private RedisService redisService;

    @Resource
    private TiktokCookieUtil tiktokCookieUtil;

    @HttpRequestRecorder
    @GetMapping("player/video")
    public Object getVideo(@RequestParam(value = "vid", required = false) String vid, @RequestParam(value = "ratio", required = false, defaultValue = "540p") String ratio, @RequestParam(value = "isDownload", required = false, defaultValue = "0") String isDownload, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        if (ObjectUtils.isEmpty(vid)) {
            return formatRespData(FAILURE, null);
        }
        if (ObjectUtils.isEmpty(ratio) || Objects.equals(ratio, "default")) {
            ratio = "540p";
        }
        String link = "https://aweme.snssdk.com/aweme/v1/play/?video_id=" + vid + "&line=0&ratio=" + ratio + "&media_type=4&vr_type=0&improve_bitrate=0&is_play_url=1&is_support_h265=0&source=PackSourceEnum_PUBLISH";
        String redirectUrl = HttpClientUtil.doGetRedirectLocation(link, HeaderUtil.getDouYinDownloadHeader(), null);
        logger.info("[getVideo] inputUrl: {}, redirectUrl: {}", link, redirectUrl);
        if (ObjectUtils.isEmpty(redirectUrl)) {
            return formatRespData(FAILURE, null);
        }
        if ("0".equals(isDownload)) {
            redirectStrategy.sendRedirect(request, response, "/tools/DouYin/preview/video?path=" + Base64Utils.encodeToUrlSafeString(redirectUrl.getBytes(StandardCharsets.UTF_8)));
        } else {
            HttpClientUtil.doRelay(redirectUrl, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockVideoHeader(true), request, response);
        }
        return formatRespData(FAILURE, null);
    }

    @HttpRequestRecorder
    @GetMapping("preview/video")
    public void previewVideo(@RequestParam String path, @RequestParam(value = "isDownload", required = false, defaultValue = "false") Boolean isDownload, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[previewVideo] inputUrl: {}, Sec-Fetch-Dest: {}", url, request.getHeader("Sec-Fetch-Dest"));
        HttpClientUtil.doRelay(url, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockVideoHeader(isDownload), request, response);
    }

    @HttpRequestRecorder
    @GetMapping("preview/liveStream")
    public void previewLiveStream(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[previewLive] inputUrl: {}, Sec-Fetch-Dest: {}", url, request.getHeader("Sec-Fetch-Dest"));
        HttpClientUtil.doRelay(url, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockLiveStreamHeader(), request, response);
    }

    @HttpRequestRecorder
    @GetMapping("download/music")
    public void downloadMusic(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[previewLive] inputUrl: {}, Sec-Fetch-Dest: {}", url, request.getHeader("Sec-Fetch-Dest"));
        HttpClientUtil.doRelay(url, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockMusicHeader(true), request, response);
    }

    @HttpRequestRecorder
    @GetMapping(value = "api", produces = {"application/json;charset=utf-8"})
    public Object getDouYinInfos(@MixedHttpRequest(required = false) String link, @RequestParam(value = "type", required = false, defaultValue = "info") String type, @RequestParam(value = "version", required = false, defaultValue = "4") Integer version, @RequestParam(value = "isMobile", required = false, defaultValue = "false") String isMobile, HttpServletRequest request, HttpServletResponse response) {
        if (ObjectUtils.isEmpty(link)) {
            return formatRespData(INVALID_LINK, null);
        }
        int typeId = DouYinRequestTypeEnums.getTypeIdByType(type);
        if (Objects.equals(typeId, DouYinRequestTypeEnums.INVALID_TYPE.getTypeId())) {
            return formatRespData(INVALID_TYPE, null);
        }
        String url;
        Optional<String> optional = Arrays.stream(link.split(" ")).filter(item -> item.contains("douyin.com/")).findFirst();
        if (optional.isPresent()) {
            url = optional.get().trim();
        } else {
            return formatRespData(INVALID_LINK, null);
        }
        // 初始化product
        DouYinApiBuilder builder = new ConcreteDouYinApiBuilder();
        DouYinApiManager manager = new DouYinApiManager(builder);
        DouYinApiProduct product = null;
        try {
            product = manager.construct(redisService, hostManager.getHost(), url, version, isMobile, tiktokCookieUtil.getTiktokCookie());
        } catch (Exception e) {
            e.printStackTrace();
            return formatRespData(FAILURE, null);
        }
        PublicTiktokDataRespModel productData = product.generateData();
        if (!Objects.isNull(productData)) {
            try {
                switch (Objects.requireNonNull(DouYinRequestTypeEnums.getEnumsByType(type))) {
                    case DOWNLOAD:
                        if (checkCanDownload(productData.getItemTypeId())) {
                            ItemInfoRespModel tmp = productData.getItemInfoData();
                            if (!Objects.isNull(tmp) && !Objects.isNull(tmp.getAwemeDetailModel()) && !Objects.isNull(tmp.getAwemeDetailModel().getVideo()) && !ObjectUtils.isEmpty(tmp.getAwemeDetailModel().getVideo().getMockDownloadVidPath())) {
                                redirectStrategy.sendRedirect(request, response, tmp.getAwemeDetailModel().getVideo().getMockDownloadVidPath());
                            }
                        } else {
                            return formatRespData(UNSUPPORTED_OPERATION, null);
                        }
                        break;
                    case PREVIEW:
                        if (checkCanPreview(productData.getItemTypeId())) {
                            DouYinTypeEnums douYinTypeEnum = DouYinTypeEnums.getEnumsByCode(productData.getItemTypeId());
                            switch (Objects.requireNonNull(douYinTypeEnum)) {
                                case MUSIC_TYPE -> {
                                    MusicInfoRespModel tmp = productData.getMusicItemInfoData();
                                    if (!Objects.isNull(tmp) && !Objects.isNull(tmp.getAwemeMusicDetail()) && !Objects.isNull(tmp.getAwemeMusicDetail().get(0)) && !Objects.isNull(tmp.getAwemeMusicDetail().get(0).getMusic()) && !ObjectUtils.isEmpty(tmp.getAwemeMusicDetail().get(0).getMusic().getMockPreviewMusicPath())) {
                                        redirectStrategy.sendRedirect(request, response, tmp.getAwemeMusicDetail().get(0).getMusic().getMockPreviewMusicPath());
                                    }
                                }
                                case VIDEO_TYPE -> {
                                    ItemInfoRespModel tmp = productData.getItemInfoData();
                                    if (!Objects.isNull(tmp) && !Objects.isNull(tmp.getAwemeDetailModel()) && !Objects.isNull(tmp.getAwemeDetailModel().getVideo()) && !ObjectUtils.isEmpty(tmp.getAwemeDetailModel().getVideo().getMockPreviewVidPath())) {
                                        redirectStrategy.sendRedirect(request, response, tmp.getAwemeDetailModel().getVideo().getMockPreviewVidPath());
                                    }
                                }
                                case LIVE_TYPE_1, LIVE_TYPE_2 -> {
                                    RoomInfoDataRespModel tmp = productData.getRoomItemInfoData();
                                    if (!Objects.isNull(tmp) && !Objects.isNull(tmp.getData()) && !Objects.isNull(tmp.getData().getData()) && !Objects.isNull(tmp.getData().getData().get(0)) && !Objects.isNull(tmp.getData().getData().get(0).getStreamUrl()) && StringUtils.hasLength(tmp.getData().getData().get(0).getStreamUrl().getMockPreviewLivePath())) {
                                        redirectStrategy.sendRedirect(request, response, tmp.getData().getData().get(0).getStreamUrl().getMockPreviewLivePath());
                                    }
                                }
                                default -> {
                                    return formatRespData(UNSUPPORTED_OPERATION, null);
                                }
                            }

                        } else {
                            return formatRespData(UNSUPPORTED_OPERATION, null);
                        }
                        break;
                    case INFO, INVALID_TYPE:
                        break;
                }
                return formatRespData(GET_DATA_SUCCESS, productData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formatRespData(GET_INFO_ERROR, null);
    }

    @HttpRequestRecorder
    @GetMapping(value = "api/feed/v1", produces = {"application/json;charset=utf-8"})
    public String getFeedV1() throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("cached_item_num", "0");
        params.put("device_type", "MI 5s");
        params.put("device_platform", "android");
        params.put("version_code", "290");
        params.put("app_name", "douyin_lite");
        params.put("os_version", "12.0.0");
        params.put("channel", "tengxun");
        String response = HttpClientUtil.doGet(TIKTOK_FEED_RECOMMEND_V1, HeaderUtil.getDouYinFeedSpecialHeader(), params);
        return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @HttpRequestRecorder
    @GetMapping(value = "api/feed/recommend/v2", produces = {"application/json;charset=utf-8"})
    public String getRecommendFeedV2() throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("type", "0");
        params.put("max_cursor", "0");
        params.put("min_cursor", "0");
        params.put("count", "6");
        params.put("volume", "0.8666666666666667");
        params.put("pull_type", "2");
        params.put("need_relieve_aweme", "0");
        params.put("filter_warn", "0");
        params.put("req_from", "");
        params.put("is_cold_start", "0");
        params.put("iid", "84579705899");
        params.put("device_id", "69367187550");
        params.put("ac", "wifi");
        params.put("channel", "douyin_lite_gw");
        params.put("aid", "2329");
        params.put("app_name", "douyin_lite");
        params.put("version_code", "180");
        params.put("version_name", "1.8.0");
        params.put("device_platform", "android");
        params.put("ssmix", "a");
        params.put("device_type", "Redmi+Note+7+Pro");
        params.put("device_brand", "Xiaomi");
        params.put("language", "zh");
        params.put("os_api", "28");
        params.put("os_version", "9");
        params.put("openudid", "e4680b0d0446ad09");
        params.put("manifest_version_code", "180");
        params.put("resolution", "1080*2119");
        params.put("dpi", "440");
        params.put("_rticket", "");
        params.put("ts", "");
        params.put("js_sdk_version", "1.10.4");
        params.put("as", "a1iosdfgh");
        params.put("cp", "androide1");
        String response = XGorgonUtil.doGetRequest(TIKTOK_FEED_RECOMMEND_V2, params);
        return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
    }

    @SuppressWarnings("SpellCheckingInspection")
    @HttpRequestRecorder
    @GetMapping(value = "api/feed/nearby/v2", produces = {"application/json;charset=utf-8"})
    public String getNearbyFeedV2() throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("feed_style", "1");
        params.put("max_cursor", "0");
        params.put("min_cursor", "0");
        params.put("count", "6");
        params.put("retry_type", "no_retry");
        params.put("iid", "84579705899");
        params.put("device_id", "69367187550");
        params.put("ac", "wifi");
        params.put("channel", "douyin_lite_gw");
        params.put("aid", "2329");
        params.put("app_name", "douyin_lite");
        params.put("version_code", "180");
        params.put("version_name", "1.8.0");
        params.put("device_platform", "android");
        params.put("ssmix", "a");
        params.put("device_type", "Redmi+Note+7+Pro");
        params.put("device_brand", "Xiaomi");
        params.put("language", "zh");
        params.put("os_api", "28");
        params.put("os_version", "9");
        params.put("openudid", "e4680b0d0446ad09");
        params.put("manifest_version_code", "180");
        params.put("resolution", "1080*2119");
        params.put("dpi", "440");
        params.put("update_version_code", "1800");
        params.put("_rticket", "");
        params.put("ts", "");
        params.put("js_sdk_version", "1.10.4");
        params.put("as", "a1iosdfgh");
        params.put("cp", "androide1");
        String response = XGorgonUtil.doGetRequest(TIKTOK_FEED_NEARBY_V2, params);
        return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
    }

    @HttpRequestRecorder
    @GetMapping("set/ttwid")
    public String setToken(@RequestParam(required = false) String ttwid) {
        redisService.set(TIKTOK_TTWID_DATA, ttwid);
        return redisService.getAndPersist(TIKTOK_TTWID_DATA);
    }

    @HttpRequestRecorder
    @GetMapping("reset/cookie")
    public void resetCookie(@RequestParam(required = false) String lock) {
        redisService.set(TIKTOK_COOKIE_LOCK, lock, 14 * 24 * 60 * 60L);
    }

    @HttpRequestRecorder
    @GetMapping("current/cookie")
    public String getCookie() {
        return redisService.get(TIKTOK_COOKIE_DATA);
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void refreshToken() {
        tiktokCookieUtil.doRefreshTiktokCookieTask();
    }

    private Boolean checkCanDownload(Integer itemTypeId) {
        return itemTypeId.equals(VIDEO_TYPE.getCode());
    }

    private Boolean checkCanPreview(Integer itemTypeId) {
        return itemTypeId.equals(VIDEO_TYPE.getCode()) || itemTypeId.equals(LIVE_TYPE_1.getCode()) || itemTypeId.equals(LIVE_TYPE_2.getCode()) || itemTypeId.equals(MUSIC_TYPE.getCode());
    }

}
