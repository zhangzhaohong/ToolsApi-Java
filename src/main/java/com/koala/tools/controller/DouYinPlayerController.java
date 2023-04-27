package com.koala.tools.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import java.util.Arrays;

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
    public String picture(Model model) {
        model.addAttribute("data", Arrays.asList(
                "https://p6-pc-sign.douyinpic.com/tos-cn-i-0813c001/cc3081c4104a4e10b13bce816e5e1263~tplv-dy-aweme-images:q75.webp?x-expires=1683784800&x-signature=ZAlRRBk%2BE0fl0%2FKICpRPVczyJXQ%3D&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&se=false&biz_tag=aweme_images&l=202304271407140F4EB787850D910C64A3",
                "https://p9-pc-sign.douyinpic.com/tos-cn-i-0813c001/cc3081c4104a4e10b13bce816e5e1263~tplv-dy-aweme-images:q75.webp?x-expires=1683784800&x-signature=6cEk5RbWXzlAz5ZAXo3xhYMwJyo%3D&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&se=false&biz_tag=aweme_images&l=202304271407140F4EB787850D910C64A3",
                "https://p3-pc-sign.douyinpic.com/tos-cn-i-0813c001/cc3081c4104a4e10b13bce816e5e1263~tplv-dy-aweme-images:q75.webp?x-expires=1683784800&x-signature=bmqHdcEGtSTlrbtYuVQNjSy2a7A%3D&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&se=false&biz_tag=aweme_images&l=202304271407140F4EB787850D910C64A3",
                "https://p6-pc-sign.douyinpic.com/tos-cn-i-0813c001/cc3081c4104a4e10b13bce816e5e1263~tplv-dy-aweme-images:q75.jpeg?x-expires=1683784800&x-signature=NDsghNI9lRgIRzJGVqKzsW%2BMn%2BI%3D&from=3213915784&s=PackSourceEnum_AWEME_DETAIL&se=false&biz_tag=aweme_images&l=202304271407140F4EB787850D910C64A3"
        ));
        return "picture/index";
    }

}
