package com.koala.web.controller;

import com.koala.data.models.shortUrl.ShortKugouItemDataModel;
import com.koala.service.custom.http.annotation.HttpRequestRecorder;
import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.Base64Utils;
import com.koala.service.utils.GsonUtil;
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

import static com.koala.service.data.redis.RedisKeyPrefix.KUGOU_DATA_KEY_PREFIX;

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

    @HttpRequestRecorder
    @GetMapping("/music/short")
    public String musicWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, @RequestParam(value = "key", required = false, defaultValue = "default") String quality, @RequestParam(value = "version", required = false, defaultValue = "1") String version, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[musicPlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortKugouItemDataModel tmp = GsonUtil.toBean(redisService.get(KUGOU_DATA_KEY_PREFIX + itemKey), ShortKugouItemDataModel.class);
                String artist = StringUtils.hasLength(tmp.getAuthorName()) ? " - " + tmp.getAuthorName() : "";
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() + artist : "MusicPlayer");
                // model.addAttribute("type", "audio/" + tmp.getType());
                if ("1".equals(version)) {
                    return "music/plyr/netease/index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

}
