package com.koala.web.controller;

import com.koala.base.enums.DouYinRequestTypeEnums;
import com.koala.base.enums.NeteaseRequestTypeEnums;
import com.koala.data.models.netease.NeteaseMusicDataRespModel;
import com.koala.factory.builder.ConcreteNeteaseApiBuilder;
import com.koala.factory.builder.NeteaseApiBuilder;
import com.koala.factory.director.NeteaseApiManager;
import com.koala.factory.extra.NeteaseCookieUtil;
import com.koala.factory.product.NeteaseApiProduct;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.data.redis.service.RedisService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.koala.base.enums.NeteaseResponseEnums.*;
import static com.koala.service.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 14:13
 * @description
 */
@RestController
@RequestMapping("tools/Netease")
public class NeteaseToolsController {

    private static final Logger logger = LoggerFactory.getLogger(NeteaseToolsController.class);

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource(name = "getHost")
    private String host;

    @Resource(name = "RedisService")
    private RedisService redisService;

    @Resource
    private NeteaseCookieUtil neteaseCookieUtil;

    @HttpRequestRecorder
    @GetMapping(value = "api", produces = {"application/json;charset=utf-8"})
    public Object getNeteaseMusic(@RequestParam(required = false) String link, @RequestParam(required = false, name = "type", defaultValue = "info") String type, @RequestParam(required = false, name = "version", defaultValue = "1") String version, HttpServletRequest request, HttpServletResponse response) {
        if (!StringUtils.hasLength(link)) {
            return formatRespData(INVALID_LINK, null);
        }
        String url;
        Optional<String> optional = Arrays.stream(link.split(" ")).filter(item -> item.contains("music.163.com/")).findFirst();
        if (optional.isPresent()) {
            url = optional.get().trim();
        } else {
            return formatRespData(INVALID_LINK, null);
        }
        NeteaseApiBuilder builder = new ConcreteNeteaseApiBuilder();
        NeteaseApiManager manager = new NeteaseApiManager(builder);
        NeteaseApiProduct product = null;
        try {
            product = manager.construct(redisService, host, neteaseCookieUtil.getNeteaseCookie(), url, Integer.valueOf(version));
        } catch (Exception e) {
            e.printStackTrace();
            return formatRespData(FAILURE, null);
        }
        NeteaseMusicDataRespModel publicData = product.generateItemInfoRespData();
        try {
            switch (Objects.requireNonNull(NeteaseRequestTypeEnums.getEnumsByType(type))) {
                case INFO -> {
                    return formatRespData(GET_DATA_SUCCESS, publicData);
                }
                default -> {
                    return formatRespData(UNSUPPORTED_TYPE, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatRespData(GET_INFO_ERROR, null);
    }

}
