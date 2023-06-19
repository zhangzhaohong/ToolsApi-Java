package com.koala.web.controller;

import com.koala.base.enums.NeteaseResponseEnums;
import com.koala.factory.path.KugouWebPathCollector;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HeaderUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.service.utils.KugouSignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static com.koala.base.enums.KugouResponseEnums.*;
import static com.koala.factory.extra.kugou.KugouSearchParamsGenerator.getSearchParams;
import static com.koala.factory.extra.kugou.KugouSearchParamsGenerator.getSearchTextParams;
import static com.koala.factory.path.KugouWebPathCollector.KUGOU_DETAIL_SERVER_URL;
import static com.koala.factory.path.KugouWebPathCollector.KUGOU_SEARCH_WEB_SERVER_URL_V1;
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

    @HttpRequestRecorder
    @GetMapping(value = "api/search", produces = {"application/json;charset=utf-8"})
    public String search(@RequestParam(required = false) String key, @RequestParam(required = false, defaultValue = "1") Long page, @RequestParam(required = false, defaultValue = "30") Long limit) throws IOException, URISyntaxException {
        Long timestamp = System.currentTimeMillis();
        String signature = Objects.requireNonNull(KugouSignatureUtil.encrypt(getSearchTextParams(timestamp, key, page, limit))).getSignature();
        if (!StringUtils.hasLength(signature)) {
            return formatRespData(GET_SIGNATURE_FAILED, null);
        }
        String response = HttpClientUtil.doGet(KUGOU_SEARCH_WEB_SERVER_URL_V1, HeaderUtil.getKugouPublicWithOutCookieHeader(), getSearchParams(timestamp, key, page, limit, signature));
        if (StringUtils.hasLength(response)) {
            return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
        }
        return formatRespData(GET_INFO_ERROR, null);
    }

    @HttpRequestRecorder
    @GetMapping(value = "api", produces = {"application/json;charset=utf-8"})
    public String api(@RequestParam(required = false) String hash, @RequestParam(required = false) String albumId) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<>();
        params.put("r", "play/getdata");
        params.put("hash", hash);
        params.put("mid", "ed88352145d854ebf55490805de8c8a1");
        params.put("album_id", albumId);
        String response = HttpClientUtil.doGet(KUGOU_DETAIL_SERVER_URL, HeaderUtil.getKugouPublicWithOutCookieHeader(), params);
        return formatRespData(GET_DATA_SUCCESS, GsonUtil.toBean(response, Object.class));
    }
}
