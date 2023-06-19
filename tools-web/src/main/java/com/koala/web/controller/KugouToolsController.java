package com.koala.web.controller;

import com.koala.factory.path.KugouWebPathCollector;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.utils.GsonUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.service.utils.KugouSignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static com.koala.factory.extra.kugou.KugouSearchParamsGenerator.getSearchParams;
import static com.koala.factory.extra.kugou.KugouSearchParamsGenerator.getSearchTextParams;
import static com.koala.factory.path.KugouWebPathCollector.KUGOU_SEARCH_WEB_SERVER_URL_V1;

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
    public Object search(@RequestParam(required = false) String key, @RequestParam(required = false, defaultValue = "1") Long page, @RequestParam(required = false, defaultValue = "30") Long limit) throws IOException, URISyntaxException {
        Long timestamp = System.currentTimeMillis();
        String signature = Objects.requireNonNull(KugouSignatureUtil.encrypt(getSearchTextParams(timestamp, key, page, limit))).getSignature();
        String response = HttpClientUtil.doGet(KUGOU_SEARCH_WEB_SERVER_URL_V1, null, getSearchParams(timestamp, key, page, limit, signature));
        return GsonUtil.toBean(response, Object.class);
    }
}
