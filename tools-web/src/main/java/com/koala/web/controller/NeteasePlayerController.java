package com.koala.web.controller;

import com.koala.data.models.shortUrl.ShortNeteaseItemDataModel;
import com.koala.data.models.shortUrl.ShortNeteaseMvItemDataModel;
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

import static com.koala.service.data.redis.RedisKeyPrefix.NETEASE_DATA_KEY_PREFIX;
import static com.koala.service.data.redis.RedisKeyPrefix.NETEASE_MV_DATA_KEY_PREFIX;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 15:33
 * @description
 */
@Controller
@RequestMapping("tools/Netease/pro/player")
public class NeteasePlayerController {

    private static final Logger logger = LoggerFactory.getLogger(NeteasePlayerController.class);

    @Resource(name = "RedisService")
    private RedisService redisService;

    @HttpRequestRecorder
    @GetMapping("/music/short")
    public String musicWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, @RequestParam(value = "version", required = false, defaultValue = "1") String version, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[musicPlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortNeteaseItemDataModel tmp = GsonUtil.toBean(redisService.get(NETEASE_DATA_KEY_PREFIX + itemKey), ShortNeteaseItemDataModel.class);
                String artist = StringUtils.hasLength(tmp.getArtist()) ? " - " + tmp.getArtist() : "";
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() + artist : "MusicPlayer");
                model.addAttribute("path", tmp.getPath());
                model.addAttribute("type", "audio/" + tmp.getType());
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

    @HttpRequestRecorder
    @GetMapping("/mv/short")
    public String videoWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, @RequestParam(value = "version", required = false, defaultValue = "1") String version, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[videoPlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortNeteaseMvItemDataModel tmp = GsonUtil.toBean(redisService.get(NETEASE_MV_DATA_KEY_PREFIX + itemKey), ShortNeteaseMvItemDataModel.class);
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() : "VideoPlayer");
                model.addAttribute("path", tmp.getPath());
                model.addAttribute("multi", tmp.getMultiMvQualityInfo());
                if ("1".equals(version)) {
                    return "video/dplayer/netease/index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

}
