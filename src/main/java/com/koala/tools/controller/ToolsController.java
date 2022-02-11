package com.koala.tools.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 16:30
 * @description
 */
@RestController
@RequestMapping("tools")
public class ToolsController {

    @GetMapping("LanZou/api")
    public Objects getLanZouInfos(
            @RequestParam("url") String url,
            @RequestParam(value = "password") String password
    ) {
        return null;
    }
}
