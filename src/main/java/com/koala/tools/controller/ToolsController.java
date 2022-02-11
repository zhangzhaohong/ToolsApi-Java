package com.koala.tools.controller;

import com.koala.tools.enums.LanZouTypeEnums;
import com.koala.tools.enums.ResponseEnums;
import com.koala.tools.models.lanzou.RespModel;
import com.koala.tools.utils.GsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ToolsController.class);

    @GetMapping("LanZou/api")
    public Object getLanZouInfos(@RequestParam(value = "url", required = false) String url, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "type", required = false, defaultValue = "info") String type) {
        logger.info("LanZouApi: params: {url={}, password={}, type={}}", url, password, type);
        if (Objects.isNull(url)) {
            return getResp(ResponseEnums.INVALID_URL, null);
        }
        int typeId = LanZouTypeEnums.getTypeIdByType(type);
        if (Objects.equals(typeId, LanZouTypeEnums.INVALID_TYPE.getTypeId())) {
            return getResp(ResponseEnums.INVALID_TYPE, null);
        }
        return null;
    }

    private String getResp(ResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(ResponseEnums.FAILURE.getCode(), ResponseEnums.FAILURE.getMessage(), data));
    }
}
