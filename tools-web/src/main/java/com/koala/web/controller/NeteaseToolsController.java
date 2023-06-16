package com.koala.web.controller;

import com.koala.factory.builder.ConcreteNeteaseApiBuilder;
import com.koala.factory.builder.NeteaseApiBuilder;
import com.koala.factory.director.NeteaseApiManager;
import com.koala.factory.product.DouYinApiProduct;
import com.koala.factory.product.NeteaseApiProduct;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.custom.http.annotation.MixedHttpRequest;
import com.koala.service.data.redis.service.RedisService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.koala.base.enums.DouYinResponseEnums.FAILURE;
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

    @HttpRequestRecorder
    @GetMapping(value = "api", produces = {"application/json;charset=utf-8"})
    public Object getNeteaseMusic(@MixedHttpRequest(required = false) String link) {
        String url = link;
        NeteaseApiBuilder builder = new ConcreteNeteaseApiBuilder();
        NeteaseApiManager manager = new NeteaseApiManager(builder);
        NeteaseApiProduct product = null;
        try {
            product = manager.construct(redisService, host, url, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return formatRespData(FAILURE, null);
        }
        return "ok";
    }

}
