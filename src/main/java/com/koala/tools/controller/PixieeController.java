package com.koala.tools.controller;

import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.models.pixiee.ProductInfoModel;
import com.koala.tools.utils.GsonUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/20 11:07
 * @description
 */
@RestController
@RequestMapping("tools/pixiee")
public class PixieeController {
    @PostMapping(value = "getInfo", produces = {"application/json;charset=utf-8"})
    public String getInfo(@MixedHttpRequest String description, @MixedHttpRequest String material, @MixedHttpRequest String packageInfo, @MixedHttpRequest String pockets, @MixedHttpRequest String type, @MixedHttpRequest String caring) {
        return GsonUtil.toString(new ProductInfoModel(description, material, packageInfo, pockets, type, caring));
    }

}