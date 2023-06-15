package com.koala.web.controller;

import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.Base64Utils;
import com.koala.service.utils.ShortKeyGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.annotation.Resource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.koala.base.enums.PublicResponseEnums.*;
import static com.koala.service.data.redis.RedisKeyPrefix.SHORT_KEY_PREFIX;
import static com.koala.service.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/28 18:05
 * @description
 */
@Controller
public class ShortUrlController {
    private static final Logger logger = LoggerFactory.getLogger(ShortUrlController.class);
    @Resource(name = "getHost")
    private String host;

    @Resource(name = "RedisService")
    private RedisService redisService;

    @HttpRequestRecorder
    @GetMapping("/short")
    public String shortUrl(@RequestParam(value = "key", required = false, defaultValue = "") String key, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        if (StringUtils.hasLength(key)) {
            try {
                String itemKey = new String(Base64Utils.decodeFromUrlSafeString(key));
                if (StringUtils.hasLength(itemKey)) {
                    String url = redisService.get(SHORT_KEY_PREFIX + itemKey);
                    if (Objects.isNull(url)) {
                        response.setStatus(HttpStatus.SC_NOT_FOUND);
                        return "404/index";
                    }
                    logger.info("[shortUrl] itemKey: {}, url: {}, Sec-Fetch-Dest: {}", itemKey, url, request.getHeader("Sec-Fetch-Dest"));
                    response.sendRedirect(url);
                    return null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

    @HttpRequestRecorder
    @GetMapping("/generator/short")
    @ResponseBody
    public String generateShortUrl(@RequestParam(value = "url", required = false, defaultValue = "") String url, @RequestParam(value = "expire", required = false) Long expire) {
        try {
            if (StringUtils.hasLength(url)) {
                return formatRespData(GET_DATA_SUCCESS, ShortKeyGenerator.generateShortUrl(url, expire, host, redisService));
            } else {
                return formatRespData(INVALID_LINK, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatRespData(FAILURE, null);
    }
}
