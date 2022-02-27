package com.koala.tools.controller;

import com.koala.tools.config.BasicConfigProperties;
import com.koala.tools.enums.DouYinRequestTypeEnums;
import com.koala.tools.factory.builder.ConcreteDouYinApiBuilder;
import com.koala.tools.factory.builder.DouYinApiBuilder;
import com.koala.tools.factory.director.DouYinApiManager;
import com.koala.tools.factory.product.DouYinApiProduct;
import com.koala.tools.models.douyin.ItemInfoRespModel;
import com.koala.tools.utils.HeaderUtil;
import com.koala.tools.utils.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.koala.tools.enums.DouYinResponseEnums.*;
import static com.koala.tools.enums.DouYinResponseEnums.INVALID_TYPE;
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

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final BasicConfigProperties basicConfigProperties;

    public DouYinToolsController(BasicConfigProperties basicConfigProperties) {
        this.basicConfigProperties = basicConfigProperties;
    }

    @GetMapping("player/video")
    public Object getVideo(@RequestParam(value = "vid", required = false) String vid, @RequestParam(value = "ratio", required = false, defaultValue = "540p") String ratio, @RequestParam(value = "isDownload", required = false, defaultValue = "0") String isDownload, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        if (StringUtils.isEmpty(vid)) {
            return formatRespData(FAILURE, null);
        }
        if (StringUtils.isEmpty(ratio) || Objects.equals(ratio, "default")) {
            ratio = "540p";
        }
        String link = "https://aweme.snssdk.com/aweme/v1/play/?video_id=" + vid + "&line=0&ratio=" + ratio + "&media_type=4&vr_type=0&improve_bitrate=0&is_play_url=1&is_support_h265=0&source=PackSourceEnum_PUBLISH";
        String redirectUrl = HttpClientUtil.doGetRedirectLocation(link, HeaderUtil.getDouYinDownloadHeader(), null);
        logger.info("[getVideo] inputUrl: {}, redirectUrl: {}", link, redirectUrl);
        if (StringUtils.isEmpty(redirectUrl)) {
            return formatRespData(FAILURE, null);
        }
        if (isDownload.equals("0")) {
            redirectStrategy.sendRedirect(request, response, "/tools/DouYin/previewVideo?livePath=" + Base64Utils.encodeToUrlSafeString(redirectUrl.getBytes(StandardCharsets.UTF_8)));
        } else {
            HttpClientUtil.doRelay(redirectUrl, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockVideoHeader(true), request, response);
        }
        return formatRespData(FAILURE, null);
    }

    @GetMapping("previewVideo")
    public void previewVideo(@RequestParam String livePath, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        String url = new String(Base64Utils.decodeFromUrlSafeString(livePath));
        logger.info("[previewVideo] inputUrl: {}, Sec-Fetch-Dest: {}", url, request.getHeader("Sec-Fetch-Dest"));
        HttpClientUtil.doRelay(url, HeaderUtil.getDouYinDownloadHeader(), null, 206, HeaderUtil.getMockVideoHeader(false), request, response);
    }

    @GetMapping("api")
    public Object getDouYinInfos(@RequestParam(value = "link", required = false) String link, @RequestParam(value = "type", required = false, defaultValue = "info") String type, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        if (StringUtils.isEmpty(link)) {
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
        DouYinApiProduct product = manager.construct(basicConfigProperties.getHost(), url);
        if (!Objects.isNull(product.getItemInfo())) {
            if (!Objects.equals(product.getItemInfo().getStatusCode(), 0)) {
                return formatRespData(GET_INFO_ERROR, null);
            } else {
                try {
                    ItemInfoRespModel productData = product.generateData();
                    switch (Objects.requireNonNull(DouYinRequestTypeEnums.getEnumsByType(type))) {
                        case DOWNLOAD:
                            if (!Objects.isNull(productData) && !Objects.isNull(productData.getItemList()) && !productData.getItemList().isEmpty() && !Objects.isNull(productData.getItemList().get(0).getVideo()) && !StringUtils.isEmpty(productData.getItemList().get(0).getVideo().getMockDownloadVidPath())) {
                                redirectStrategy.sendRedirect(request, response, productData.getItemList().get(0).getVideo().getMockDownloadVidPath());
                            }
                            break;
                        case PREVIEW:
                            if (!Objects.isNull(productData) && !Objects.isNull(productData.getItemList()) && !productData.getItemList().isEmpty() && !Objects.isNull(productData.getItemList().get(0).getVideo()) && !StringUtils.isEmpty(productData.getItemList().get(0).getVideo().getMockPreviewVidPath())) {
                                redirectStrategy.sendRedirect(request, response, productData.getItemList().get(0).getVideo().getMockPreviewVidPath());
                            }
                            break;
                        case INFO:
                        case INVALID_TYPE:
                        default:
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return formatRespData(GET_DATA_SUCCESS, product.generateData());
            }
        } else {
            return formatRespData(GET_INFO_ERROR, null);
        }
        // return formatRespData(FAILURE, null);
    }
}
