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

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/24 11:18
 * @description
 */
@Controller
@RequestMapping("tools/DouYin/pro/player")
public class DouYinVideoController {

    private static final Logger logger = LoggerFactory.getLogger(DouYinVideoController.class);

    @GetMapping("/video")
    public String video(@RequestParam(value = "title", required = false, defaultValue = "VideoPlayer") String title, @RequestParam(value = "livePath", required = false) String livePath, Model model, HttpServletRequest request) {
        String itemTitle = "VideoPlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(livePath));
        logger.info("[previewVideo] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        return "video/index";
    }

    @GetMapping("/live")
    public String live(@RequestParam(value = "title", required = false, defaultValue = "LivePlayer") String title, @RequestParam(value = "livePath", required = false) String livePath, Model model, HttpServletRequest request) {
        String itemTitle = "LivePlayer".equals(title) ? title : new String(Base64Utils.decodeFromUrlSafeString(title));
        String url = new String(Base64Utils.decodeFromUrlSafeString(livePath));
        logger.info("[previewLive] itemTitle: {}, inputUrl: {}, Sec-Fetch-Dest: {}", itemTitle, url, request.getHeader("Sec-Fetch-Dest"));
        model.addAttribute("title", itemTitle);
        model.addAttribute("path", url);
        return "live/index";
    }


}
