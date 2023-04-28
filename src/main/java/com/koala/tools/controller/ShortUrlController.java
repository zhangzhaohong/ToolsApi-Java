package com.koala.tools.controller;

import com.koala.tools.redis.service.RedisService;
import com.koala.tools.utils.ShortKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.koala.tools.enums.PublicResponseEnums.*;
import static com.koala.tools.utils.RespUtil.formatRespData;

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

    @GetMapping("/short")
    public String shortUrl(@RequestParam(value = "key", required = false, defaultValue = "") String key, HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException {
        try {
            String itemKey = new String(Base64Utils.decodeFromUrlSafeString(key));
            if (StringUtils.hasLength(itemKey)) {
                String url = redisService.get(itemKey);
                if (Objects.isNull(url)) {
                    return "404/index";
                }
                logger.info("[shortUrl] itemKey: {}, url: {}, Sec-Fetch-Dest: {}", itemKey, url, request.getHeader("Sec-Fetch-Dest"));
                response.sendRedirect(url);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404/index";
    }

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
