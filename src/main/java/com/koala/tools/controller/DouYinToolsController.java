package com.koala.tools.controller;

import com.koala.tools.factory.builder.ConcreteDouYinApiBuilder;
import com.koala.tools.factory.builder.DouYinApiBuilder;
import com.koala.tools.factory.director.DouYinApiManager;
import com.koala.tools.factory.product.DouYinApiProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import static com.koala.tools.enums.DouYinResponseEnums.*;
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
    public Object getDouYinInfos(@RequestParam(value = "link", required = false) String link, HttpServletResponse response) throws IOException, URISyntaxException {
        if (StringUtils.isEmpty(link)) {
            return formatRespData(INVALID_LINK, null);
        }
        String url;
        Optional<String> optional = Arrays.stream(link.split(" ")).filter(item -> item.contains("douyin.com/")).findFirst();
        if (optional.isPresent()) {
            url = optional.get().trim();
        } else {
            return formatRespData(INVALID_LINK, null);
        }
        // 初始化product
        DouYinApiBuilder builder = new ConcreteDouYinApiBuilder();
        DouYinApiManager manager = new DouYinApiManager(builder);
        DouYinApiProduct product = manager.construct(url);
        try {
            if (!Objects.isNull(product.getItemInfo())) {
                if (!Objects.equals(product.getItemInfo().getStatusCode(), 0)) {
                    return formatRespData(GET_INFO_ERROR, null);
                } else {
                    return formatRespData(GET_DATA_SUCCESS, product.generateData());
                }
            } else {
                return formatRespData(GET_INFO_ERROR, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatRespData(FAILURE, null);
    }
}
