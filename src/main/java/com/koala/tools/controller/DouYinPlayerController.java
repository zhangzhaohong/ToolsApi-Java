package com.koala.tools.controller;

import com.koala.tools.http.annotation.HttpRequestRecorder;
import com.koala.tools.models.shortUrl.ShortDouYinItemDataModel;
import com.koala.tools.models.shortUrl.ShortImageDataModel;
import com.koala.tools.redis.service.RedisService;
import com.koala.tools.utils.GsonUtil;
import org.apache.http.HttpStatus;
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
import javax.servlet.http.HttpServletResponse;

import static com.koala.tools.redis.RedisKeyPrefix.TIKTOK_DATA_KEY_PREFIX;

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

    @HttpRequestRecorder
    @GetMapping("/video")
    public String video(@RequestParam(value = "title", required = false, defaultValue = "VideoPlayer") String title, @RequestParam(value = "path", required = false, defaultValue = "") String path, @RequestParam(value = "version", required = false, defaultValue = "2") String version, Model model, HttpServletRequest request, HttpServletResponse response) {
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
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        return "500/index";
    }

    @HttpRequestRecorder
    @GetMapping("/video/short")
    public String videoWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, @RequestParam(value = "version", required = false, defaultValue = "2") String version, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[videoPlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortDouYinItemDataModel tmp = GsonUtil.toBean(redisService.get(TIKTOK_DATA_KEY_PREFIX + itemKey), ShortDouYinItemDataModel.class);
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() : "VideoPlayer");
                model.addAttribute("path", tmp.getPath());
                if ("2".equals(version)) {
                    return "video/plyr/index";
                } else if ("1".equals(version)) {
                    return "video/video.js/index";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

    @HttpRequestRecorder
    @GetMapping("/live")
    public String live(@RequestParam(value = "title", required = false, defaultValue = "LivePlayer") String title, @RequestParam(value = "path", required = false, defaultValue = "") String path, Model model, HttpServletRequest request) {
        String itemTitle = "LivePlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[livePlayer] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        return "live/index";
    }

    @HttpRequestRecorder
    @GetMapping("/live/short")
    public String liveWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[livePlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortDouYinItemDataModel tmp = GsonUtil.toBean(redisService.get(TIKTOK_DATA_KEY_PREFIX + itemKey), ShortDouYinItemDataModel.class);
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() : "LivePlayer");
                model.addAttribute("path", tmp.getPath());
                return "live/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

    @HttpRequestRecorder
    @GetMapping("/music")
    public String music(@RequestParam(value = "title", required = false, defaultValue = "MusicPlayer") String title, @RequestParam(value = "path", required = false, defaultValue = "") String path, Model model, HttpServletRequest request) {
        String itemTitle = "MusicPlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(path));
        logger.info("[musicPlayer] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        return "music/plyr/index";
    }

    @HttpRequestRecorder
    @GetMapping("/music/short")
    public String musicWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[musicPlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortDouYinItemDataModel tmp = GsonUtil.toBean(redisService.get(TIKTOK_DATA_KEY_PREFIX + itemKey), ShortDouYinItemDataModel.class);
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() : "MusicPlayer");
                model.addAttribute("path", tmp.getPath());
                return "music/plyr/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

    @HttpRequestRecorder
    @GetMapping("picture/short")
    public String pictureWithShortKey(@RequestParam(value = "key", required = false, defaultValue = "") String key, Model model, HttpServletRequest request, HttpServletResponse response) {
        try {
            String itemKey = "".equals(key) ? "" : new String(Base64Utils.decodeFromUrlSafeString(key));
            logger.info("[picturePlayer] itemKey: {}, Sec-Fetch-Dest: {}", itemKey, request.getHeader("Sec-Fetch-Dest"));
            if (StringUtils.hasLength(itemKey)) {
                ShortImageDataModel tmp = GsonUtil.toBean(redisService.get(TIKTOK_DATA_KEY_PREFIX + itemKey), ShortImageDataModel.class);
                model.addAttribute("title", StringUtils.hasLength(tmp.getTitle()) ? tmp.getTitle() : "PicturePlayer");
                model.addAttribute("data", tmp.getData());
                return "picture/index";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setStatus(HttpStatus.SC_NOT_FOUND);
        return "404/index";
    }

}
