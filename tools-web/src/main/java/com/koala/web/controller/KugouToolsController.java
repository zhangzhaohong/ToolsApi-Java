package com.koala.web.controller;

import com.koala.factory.extra.kugou.KugouCustomParamsUtil;
import com.koala.factory.extra.kugou.KugouMidGenerator;
import com.koala.factory.extra.kugou.KugouPlayInfoParamsGenerator;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.utils.*;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.koala.base.enums.KugouResponseEnums.*;
import static com.koala.factory.extra.kugou.KugouSearchParamsGenerator.getSearchParams;
import static com.koala.factory.extra.kugou.KugouSearchParamsGenerator.getSearchTextParams;
import static com.koala.factory.path.KugouWebPathCollector.*;
import static com.koala.service.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/19 20:03
 * @description
 */
@RestController
@RequestMapping("tools/Kugou")
public class KugouToolsController {

    private static final Logger logger = LoggerFactory.getLogger(KugouToolsController.class);

    @Resource
    private KugouCustomParamsUtil customParams;

    @HttpRequestRecorder
    @GetMapping(value = "api/search", produces = {"application/json;charset=utf-8"})
    public String search(@RequestParam(required = false) String key, @RequestParam(required = false, defaultValue = "1") Long page, @RequestParam(required = false, defaultValue = "30") Long limit) throws IOException, URISyntaxException {
        Long timestamp = System.currentTimeMillis();
        String mid = KugouMidGenerator.getMid();
        String signature = MD5Utils.md5(getSearchTextParams(timestamp, key, mid, page, limit, customParams));
        if (!StringUtils.hasLength(signature)) {
            return formatRespData(GET_SIGNATURE_FAILED, null);
        }
        String response = HttpClientUtil.doGet(KUGOU_SEARCH_WEB_SERVER_URL_V1, HeaderUtil.getKugouPublicHeader(null, null), getSearchParams(timestamp, key, mid, page, limit, signature, customParams));
        if (StringUtils.hasLength(response)) {
            return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
        }
        return formatRespData(GET_INFO_ERROR, null);
    }

    @HttpRequestRecorder
    @GetMapping(value = "api/playInfo", produces = {"application/json;charset=utf-8"})
    public String playInfo(@RequestParam(required = false) String hash, @RequestParam(required = false) String albumId) throws IOException, URISyntaxException {
        String mid = KugouMidGenerator.getMid();
        String cookie = customParams.getKugouCustomParams().get("kg_mid_cookie").toString();
        String response = HttpClientUtil.doGet(KUGOU_DETAIL_SERVER_URL_V2, HeaderUtil.getKugouPublicHeader(null,cookie), KugouPlayInfoParamsGenerator.getPlayInfoParams(hash, mid, albumId, customParams));
        if (StringUtils.hasLength(response)) {
            return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
        }
        return formatRespData(GET_INFO_ERROR, null);
    }

}