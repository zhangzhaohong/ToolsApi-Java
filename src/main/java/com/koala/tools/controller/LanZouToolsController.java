package com.koala.tools.controller;

import com.koala.tools.enums.LanZouTypeEnums;
import com.koala.tools.enums.LanZouResponseEnums;
import com.koala.tools.factory.builder.ConcreteLanZouApiBuilder;
import com.koala.tools.factory.builder.LanZouApiBuilder;
import com.koala.tools.factory.director.LanZouApiManager;
import com.koala.tools.factory.product.LanZouApiProduct;
import com.koala.tools.models.file.FileInfoModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

    /**
     * @param url
     * @param password
     * @param type
     * @param response
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    @GetMapping(value = "api", produces = {"application/json;charset=utf-8"})
    public Object getLanZouInfos(@RequestParam(value = "url", required = false) String url, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "type", required = false, defaultValue = "info") String type, HttpServletResponse response) throws IOException, URISyntaxException {
        logger.info("LanZouApi: params: {url={}, password={}, type={}}", url, password, type);
        if (Boolean.FALSE.equals(checkLanZouUrl(url))) {
            return formatRespData(LanZouResponseEnums.INVALID_URL, null);
        }
        int typeId = LanZouTypeEnums.getTypeIdByType(type);
        if (Objects.equals(typeId, LanZouTypeEnums.INVALID_TYPE.getTypeId())) {
            return formatRespData(LanZouResponseEnums.INVALID_TYPE, null);
        }
        // 初始化product
        LanZouApiBuilder builder = new ConcreteLanZouApiBuilder();
        LanZouApiManager manager = new LanZouApiManager(builder);
        LanZouApiProduct product = null;
        try {
            product = manager.construct(url, password);
        } catch (Exception e) {
            e.printStackTrace();
            return formatRespData(LanZouResponseEnums.FAILURE, null);
        }
        if (Objects.isNull(product.getPageData())) {
            return formatRespData(LanZouResponseEnums.GET_DATA_ERROR, null);
        }
        Optional<Map.Entry<Integer, String>> optional = product.checkStatus().entrySet().stream().findFirst();
        if (optional.isPresent()) {
            if (Objects.equals(optional.get().getKey(), LanZouResponseEnums.GET_FILE_WITH_PASSWORD.getCode()) && StringUtils.isEmpty(password)) {
                return formatRespData(LanZouResponseEnums.GET_FILE_WITH_PASSWORD, null);
            }
            if (!Objects.equals(optional.get().getKey(), LanZouResponseEnums.GET_FILE_SUCCESS.getCode()) && !Objects.equals(optional.get().getKey(), LanZouResponseEnums.GET_FILE_WITH_PASSWORD.getCode())) {
                return formatRespDataWithCustomMsg(optional.get().getKey(), optional.get().getValue(), null);
            }
            // 处理数据
            if (Objects.equals(optional.get().getKey(), LanZouResponseEnums.GET_FILE_WITH_PASSWORD.getCode()) && !StringUtils.isEmpty(password)) {
                Object fileInfo = product.getFileWithPassword();
                if (fileInfo instanceof FileInfoModel) {
                    switch (Objects.requireNonNull(LanZouTypeEnums.getEnumsByType(type))) {
                        case DOWNLOAD:
                            if (StringUtils.isEmpty(((FileInfoModel) fileInfo).getDownloadUrl())) {
                                return formatRespData(LanZouResponseEnums.FAILURE, fileInfo);
                            } else {
                                if (!Objects.isNull(((FileInfoModel) fileInfo).getRedirectUrl())) {
                                    response.sendRedirect(((FileInfoModel) fileInfo).getRedirectUrl());
                                } else if (!Objects.isNull(((FileInfoModel) fileInfo).getDownloadUrl())) {
                                    response.sendRedirect(((FileInfoModel) fileInfo).getDownloadUrl());
                                }
                                return formatRespData(LanZouResponseEnums.REDIRECT_TO_DOWNLOAD, fileInfo);
                            }
                        case INFO:
                            return formatRespData(LanZouResponseEnums.GET_FILE_SUCCESS, fileInfo);
                        default:
                            return formatRespData(LanZouResponseEnums.INVALID_TYPE, null);
                    }
                } else if (fileInfo instanceof ArrayList) {
                    return formatRespData(LanZouResponseEnums.GET_FILE_SUCCESS, fileInfo);
                } else {
                    return formatRespData(LanZouResponseEnums.GET_FILE_ERROR_WITH_PASSWORD, null);
                }
            } else {
                FileInfoModel fileInfo = product.getFileInfo(null);
                if (Objects.isNull(fileInfo)) {
                    return formatRespData(LanZouResponseEnums.FAILURE, null);
                }
                switch (Objects.requireNonNull(LanZouTypeEnums.getEnumsByType(type))) {
                    case DOWNLOAD:
                        if (StringUtils.isEmpty(fileInfo.getDownloadUrl())) {
                            return formatRespData(LanZouResponseEnums.FAILURE, fileInfo);
                        } else {
                            if (!Objects.isNull(fileInfo.getRedirectUrl())) {
                                response.sendRedirect(fileInfo.getRedirectUrl());
                            } else if (!Objects.isNull(fileInfo.getDownloadUrl())) {
                                response.sendRedirect(fileInfo.getDownloadUrl());
                            }
                            return formatRespData(LanZouResponseEnums.REDIRECT_TO_DOWNLOAD, fileInfo);
                        }
                    case INFO:
                        return formatRespData(LanZouResponseEnums.GET_FILE_SUCCESS, fileInfo);
                    default:
                        return formatRespData(LanZouResponseEnums.INVALID_TYPE, null);
                }
            }
        }
        return formatRespData(LanZouResponseEnums.FAILURE, null);
    }

    private Boolean checkLanZouUrl(String url) {
        return !Objects.isNull(url) && url.contains(LANZOU);
    }
}
