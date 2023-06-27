package com.koala.web.controller;

import com.koala.base.enums.DouYinRequestTypeEnums;
import com.koala.base.enums.DouYinTypeEnums;
import com.koala.data.models.xbogus.XbogusDataModel;
import com.koala.factory.builder.ConcreteDouYinApiBuilder;
import com.koala.factory.builder.DouYinApiBuilder;
import com.koala.factory.director.DouYinApiManager;
import com.koala.factory.product.DouYinApiProduct;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.custom.http.annotation.MixedHttpRequest;
import com.koala.data.models.douyin.v1.PublicTiktokDataRespModel;
import com.koala.data.models.douyin.v1.itemInfo.ItemInfoRespModel;
import com.koala.data.models.douyin.v1.musicInfo.MusicInfoRespModel;
import com.koala.data.models.douyin.v1.roomInfoData.RoomInfoDataRespModel;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.koala.base.enums.DouYinResponseEnums.*;
import static com.koala.base.enums.DouYinTypeEnums.*;
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

    @Resource(name = "getHost")
    private String host;

    @Resource(name = "RedisService")
    private RedisService redisService;

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
    public Object getDouYinInfos(@MixedHttpRequest(required = false) String link, @RequestParam(value = "type", required = false, defaultValue = "info") String type, @RequestParam(value = "version", required = false, defaultValue = "4") Integer version, HttpServletRequest request, HttpServletResponse response) {
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
            product = manager.construct(redisService, host, url, version);
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
                    case INFO, INVALID_TYPE, default:
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
    @GetMapping(value = "api/feed", produces = {"application/json;charset=utf-8"})
    public String getFeed(@RequestParam(value = "count", required = false, defaultValue = "12") Integer count) throws IOException, URISyntaxException {
        String response = doGetXbogusRequest("https://www.douyin.com/aweme/v1/web/tab/feed/?" + URLEncoder.encode("device_platform=webapp&aid=6383&channel=channel_pc_web&tag_id=&share_aweme_id=&live_insert_type=&count=" + count + "&refresh_index=2&video_type_select=1&aweme_pc_rec_raw_data=%7B%22videoPrefer%22:%7B%22fsn%22:\\[%227242252960749735168%22,%2259301303675%22\\],%22like%22:\\[\\],%22halfMin%22:\\[\\],%22min%22:\\[\\]%7D,%22seo_info%22:%22https:%2F%2Fwww.douyin.com%2F%22,%22is_client%22:false,%22ff_danmaku_status%22:1,%22danmaku_switch_status%22:1%7D&globalwid=&pull_type=2&min_window=0&ug_source=&creative_id=&pc_client_type=1&version_code=170400&version_name=17.4.0&cookie_enabled=true&screen_width=1920&screen_height=1080&browser_language=zh-CN&browser_platform=MacIntel&browser_name=Chrome&browser_version=114.0.0.0&browser_online=true&engine_name=Blink&engine_version=114.0.0.0&os_name=Mac+OS&os_version=10.15.7&cpu_core_num=16&device_memory=8&platform=PC&downlink=9.6&effective_type=4g&round_trip_time=100&webid=7198450121436202500&msToken=XBdwzLkSfRTOTbEZ-sslL89QsR15Vbzz2_ln4Vqxj2gX-Twb967ymNYWIlZtlVYpnqdmezXad-dvWjHkX6kBl6p9uWr7Xpf4-I-MXY4XTDiZHZE3PtmVSg==", StandardCharsets.UTF_8));
        return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
    }

    private Boolean checkCanDownload(Integer itemTypeId) {
        return itemTypeId.equals(VIDEO_TYPE.getCode());
    }

    private Boolean checkCanPreview(Integer itemTypeId) {
        return itemTypeId.equals(VIDEO_TYPE.getCode()) || itemTypeId.equals(LIVE_TYPE_1.getCode()) || itemTypeId.equals(LIVE_TYPE_2.getCode()) || itemTypeId.equals(MUSIC_TYPE.getCode());
    }

    private String doGetXbogusRequest(String inputUrl) throws IOException, URISyntaxException {
        XbogusDataModel xbogusDataModel = XbogusUtil.encrypt(inputUrl);
        if (Objects.isNull(xbogusDataModel) || ObjectUtils.isEmpty(xbogusDataModel.getUrl())) {
            logger.error("[DouYinApi] encrypt error, encryptResult: {}", xbogusDataModel);
            throw new NullPointerException("encrypt error");
        }
        logger.info("[DouYinApi] encryptResult: {}", xbogusDataModel);
        int retryTime = 0;
        String response;
        while (retryTime < MAX_RETRY_TIMES) {
            response = HttpClientUtil.doGet(xbogusDataModel.getUrl(), HeaderUtil.getDouYinFeedSpecialHeader(xbogusDataModel.getMstoken(), xbogusDataModel.getTtwid()), null);
            if (StringUtils.hasLength(response)) {
                return response;
            }
            retryTime++;
            logger.info("[DouYinApi] Get data error, retry time: {}", retryTime);
        }
        return null;
    }
}
