package com.koala.tools.controller;

import com.koala.tools.enums.LanZouResponseEnums;
import com.koala.tools.factory.builder.ConcreteDouYinApiBuilder;
import com.koala.tools.factory.builder.DouYinApiBuilder;
import com.koala.tools.factory.director.DouYinApiManager;
import com.koala.tools.factory.product.DouYinApiProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

import static com.koala.tools.enums.DouYinResponseEnums.FAILURE;
import static com.koala.tools.enums.DouYinResponseEnums.GET_INFO_ERROR;
import static com.koala.tools.utils.RespUtil.formatRespData;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 15:33
 * @description
 */
@RestController
@RequestMapping("tools/DouYin")
public class DouYinToolsController {

    private static final Logger logger = LoggerFactory.getLogger(DouYinToolsController.class);

    @GetMapping("api")
    public Object getDouYinInfos(@RequestParam(value = "url", required = false) String url, @RequestParam(value = "type", required = false, defaultValue = "info") String type, HttpServletResponse response) throws IOException, URISyntaxException {
        // 初始化product
        DouYinApiBuilder builder = new ConcreteDouYinApiBuilder();
        DouYinApiManager manager = new DouYinApiManager(builder);
        DouYinApiProduct product = manager.construct(url);
        if (!Objects.isNull(product.getItemInfo())) {
            
        } else {
            return formatRespData(GET_INFO_ERROR, null);
        }
        return formatRespData(FAILURE, null);
    }
}
