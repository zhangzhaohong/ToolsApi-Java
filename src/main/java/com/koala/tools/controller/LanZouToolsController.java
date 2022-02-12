package com.koala.tools.controller;

import com.koala.tools.enums.LanZouTypeEnums;
import com.koala.tools.enums.ResponseEnums;
import com.koala.tools.utils.LanZouUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.koala.tools.utils.RespUtil.formatRespData;
import static com.koala.tools.utils.RespUtil.formatRespDataWithCustomMsg;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 16:30
 * @description
 */
@RestController
@RequestMapping("tools/LanZou")
public class LanZouToolsController {

    private static final Logger logger = LoggerFactory.getLogger(LanZouToolsController.class);

    private static final String LANZOU = "lanzou";

    @GetMapping("api")
    public Object getLanZouInfos(@RequestParam(value = "url", required = false) String url, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "type", required = false, defaultValue = "info") String type) throws IOException, URISyntaxException {
        logger.info("LanZouApi: params: {url={}, password={}, type={}}", url, password, type);
        if (Boolean.FALSE.equals(checkLanZouUrl(url))) {
            return formatRespData(ResponseEnums.INVALID_URL, null);
        }
        int typeId = LanZouTypeEnums.getTypeIdByType(type);
        if (Objects.equals(typeId, LanZouTypeEnums.INVALID_TYPE.getTypeId())) {
            return formatRespData(ResponseEnums.INVALID_TYPE, null);
        }
        LanZouUtil lanZouUtil = new LanZouUtil(url, password);
        if (!Objects.isNull(lanZouUtil.getResponse())) {
            return lanZouUtil.getResponse();
        }
        Optional<Map.Entry<Integer, String>> optional = lanZouUtil.checkStatus().entrySet().stream().findFirst();
        if (optional.isPresent() && !Objects.equals(optional.get().getKey(), ResponseEnums.GET_FILE_SUCCESS.getCode())){
            return formatRespDataWithCustomMsg(optional.get().getKey(), optional.get().getValue(), null);
        }

        return formatRespData(ResponseEnums.SUCCESS, lanZouUtil.checkStatus());
    }

    private Boolean checkLanZouUrl(String url) {
        return !Objects.isNull(url) && url.contains(LANZOU);
    }
}
