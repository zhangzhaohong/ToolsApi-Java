package com.koala.tools.controller;

import com.koala.tools.enums.DouYinRequestTypeEnums;
import com.koala.tools.enums.DouYinTypeEnums;
import com.koala.tools.factory.builder.ConcreteDouYinApiBuilder;
import com.koala.tools.factory.builder.DouYinApiBuilder;
import com.koala.tools.factory.director.DouYinApiManager;
import com.koala.tools.factory.product.DouYinApiProduct;
import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.douyin.v1.PublicTiktokDataRespModel;
import com.koala.tools.models.douyin.v1.itemInfo.ItemInfoRespModel;
import com.koala.tools.models.douyin.v1.roomInfoData.RoomInfoDataRespModel;
import com.koala.tools.utils.HeaderUtil;
import com.koala.tools.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.koala.tools.enums.DouYinResponseEnums.*;
import static com.koala.tools.enums.DouYinTypeEnums.*;
import static com.koala.tools.utils.RespUtil.formatRespData;

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

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource(name = "getHost")
    private String host;

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

    @GetMapping("preview/video")
    public void previewVideo(@RequestParam String path, @RequestParam(value = "isDownload", required = false, defaultValue = "false") Boolean isDownload, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[previewVideo] inputUrl: {}, Sec-Fetch-Dest: {}", url, request.getHeader("Sec-Fetch-Dest"));
        HttpClientUtil.doRelay(url, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockVideoHeader(isDownload), request, response);
    }

    @GetMapping("preview/liveStream")
    public void previewLiveStream(@RequestParam String path, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[previewLive] inputUrl: {}, Sec-Fetch-Dest: {}", url, request.getHeader("Sec-Fetch-Dest"));
        HttpClientUtil.doRelay(url, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockLiveStreamHeader(), request, response);
    }

    @GetMapping(value = "api", produces = {"application/json;charset=utf-8"})
    public Object getDouYinInfos(@MixedHttpRequest(required = false) String link, @RequestParam(value = "type", required = false, defaultValue = "info") String type, @RequestParam(value = "version", required = false, defaultValue = "3") Integer version, HttpServletRequest request, HttpServletResponse response) {
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
            product = manager.construct(host, url, version);
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
                                case VIDEO_TYPE -> {
                                    ItemInfoRespModel tmp = productData.getItemInfoData();
                                    if (!Objects.isNull(tmp) && !Objects.isNull(tmp.getAwemeDetailModel()) && !Objects.isNull(tmp.getAwemeDetailModel().getVideo()) && !ObjectUtils.isEmpty(tmp.getAwemeDetailModel().getVideo().getMockPreviewVidPath())) {
                                        redirectStrategy.sendRedirect(request, response, tmp.getAwemeDetailModel().getVideo().getMockPreviewVidPath());
                                    }
                                }
                                case LIVE_TYPE_1, LIVE_TYPE_2 -> {
                                    RoomInfoDataRespModel tmp = productData.getRoomItemInfoData();
                                    if (!Objects.isNull(tmp) && !Objects.isNull(tmp.getData()) && !Objects.isNull(tmp.getData().getData()) && !Objects.isNull(tmp.getData().getData().get(0)) && !Objects.isNull(tmp
                                            .getData().getData().get(0).getStreamUrl()) && StringUtils.hasLength(tmp.getData().getData().get(0).getStreamUrl().getMockPreviewVidPath())) {
                                        redirectStrategy.sendRedirect(request, response, tmp.getData().getData().get(0).getStreamUrl().getMockPreviewVidPath());
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

    private Boolean checkCanDownload(Integer itemTypeId) {
        return itemTypeId.equals(VIDEO_TYPE.getCode());
    }

    private Boolean checkCanPreview(Integer itemTypeId) {
        return itemTypeId.equals(VIDEO_TYPE.getCode()) || itemTypeId.equals(LIVE_TYPE_1.getCode()) || itemTypeId.equals(LIVE_TYPE_2.getCode());
    }
}
