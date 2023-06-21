package com.koala.web.controller;

import com.koala.base.enums.KugouRequestQualityEnums;
import com.koala.data.models.kugou.playInfo.KugouPlayInfoRespDataModel;
import com.koala.data.models.shortUrl.ShortKugouItemDataModel;
import com.koala.factory.extra.kugou.KugouCustomParamsUtil;
import com.koala.factory.extra.kugou.KugouMidGenerator;
import com.koala.factory.extra.kugou.KugouPlayInfoParamsGenerator;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.Base64Utils;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HeaderUtil;
import com.koala.service.utils.HttpClientUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;

import static com.koala.base.enums.KugouResponseEnums.GET_DATA_SUCCESS;
import static com.koala.factory.path.KugouWebPathCollector.KUGOU_DETAIL_SERVER_URL_V2;
import static com.koala.service.data.redis.RedisKeyPrefix.KUGOU_DATA_KEY_PREFIX;
import static com.koala.service.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/21 12:24
 * @description
 */
@Controller
@RequestMapping("tools/Kugou/pro/player")
public class KugouPlayerController {

    private static final Logger logger = LoggerFactory.getLogger(KugouPlayerController.class);

    @Resource(name = "RedisService")
    private RedisService redisService;

    @Resource
    private KugouCustomParamsUtil customParams;

    @HttpRequestRecorder
    @GetMapping("/music/short")
    public String musicWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, @RequestParam(value = "quality", required = false, defaultValue = "default") String quality, @RequestParam(value = "version", required = false, defaultValue = "1") String version, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[musicPlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (Objects.isNull(KugouRequestQualityEnums.getEnumsByType(quality))) {
                response.setStatus(HttpStatus.SC_NOT_FOUND);
                return "404/index";
            }
            if (StringUtils.hasLength(itemKey)) {
                try {
                    ShortKugouItemDataModel tmp = GsonUtil.toBean(redisService.get(KUGOU_DATA_KEY_PREFIX + itemKey), ShortKugouItemDataModel.class);
                    String artist = StringUtils.hasLength(tmp.getAuthorName()) ? " - " + tmp.getAuthorName() : "";
                    model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() + artist : "MusicPlayer");
                    String hash = tmp.getMusicInfo().getAudioInfo().getPlayInfoList().get(quality).getHash();
                    String albumId = tmp.getMusicInfo().getAlbumInfo().getAlbumId();
                    String mid = KugouMidGenerator.getMid();
                    String cookie = customParams.getKugouCustomParams().get("kg_mid_cookie").toString();
                    String resp = HttpClientUtil.doGet(KUGOU_DETAIL_SERVER_URL_V2, HeaderUtil.getKugouPublicHeader(null, cookie), KugouPlayInfoParamsGenerator.getPlayInfoParams(hash, mid, albumId, customParams));
                    KugouPlayInfoRespDataModel respData = null;
                    if (StringUtils.hasLength(resp)) {
                        respData = GsonUtil.toBean(resp, KugouPlayInfoRespDataModel.class);
                    }
                    if (Objects.isNull(respData)) {
                        response.setStatus(HttpStatus.SC_NOT_FOUND);
                        return "404/index";
                    }
                    model.addAttribute("type", "audio/" + respData.getExtName());
                    model.addAttribute("media", respData.getUrl());
                    if ("1".equals(version)) {
                        return "music/plyr/kugou/index";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

}
