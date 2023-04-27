package com.koala.tools.controller;

import com.koala.tools.models.douyin.v1.itemInfo.ImageDataModel;
import com.koala.tools.redis.service.RedisService;
import com.koala.tools.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Objects;

import static com.koala.tools.enums.DouYinResponseEnums.UNAVAILABLE_DATA;
import static com.koala.tools.enums.DouYinResponseEnums.UNAVAILABLE_PLAYER;
import static com.koala.tools.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/24 11:18
 * @description
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
@Controller
@RequestMapping("tools/DouYin/pro/player")
public class DouYinPlayerController {

    private static final Logger logger = LoggerFactory.getLogger(DouYinPlayerController.class);

    @Resource(name = "RedisService")
    private RedisService redisService;

    @GetMapping("/video")
    public String video(@RequestParam(value = "title", required = false, defaultValue = "VideoPlayer") String title, @RequestParam(value = "path", required = false, defaultValue = "") String path, @RequestParam(value = "version", required = false, defaultValue = "2") String version, Model model, HttpServletRequest request) {
        String itemTitle = "VideoPlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[videoPlayer] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        if ("2".equals(version)) {
            return "video/plyr/index";
        } else if ("1".equals(version)) {
            return "video/video.js/index";
        }
        return formatRespData(UNAVAILABLE_PLAYER, null);
    }

    @GetMapping("/live")
    public String live(@RequestParam(value = "title", required = false, defaultValue = "LivePlayer") String title, @RequestParam(value = "path", required = false, defaultValue = "") String path, Model model, HttpServletRequest request) {
        String itemTitle = "LivePlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[livePlayer] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        return "live/index";
    }

    @GetMapping("/music")
    public String music(@RequestParam(value = "title", required = false, defaultValue = "MusicPlayer") String title, @RequestParam(value = "path", required = false, defaultValue = "") String path, Model model, HttpServletRequest request) {
        String itemTitle = "MusicPlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[musicPlayer] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        return "music/plyr/index";
    }

    @GetMapping("picture")
    public String picture(@RequestParam(value = "title", required = false, defaultValue = "PicturePlayer") String title, @RequestParam(value = "key", required = false, defaultValue = "") String key, Model model) {
        String itemTitle = "PicturePlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
        if (StringUtils.hasLength(itemKey)) {
            model.addAttribute("title", itemTitle);
            model.addAttribute("data", GsonUtil.toBean(redisService.get(itemKey), ImageDataModel.class).getData());
            return "picture/index";
        }
        return formatRespData(UNAVAILABLE_DATA, null);
    }

}
