package com.koala.tools.utils;

import com.koala.tools.enums.ResponseEnums;
import com.koala.tools.models.lanzou.RespModel;

import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 23:35
 * @description
 */
public class RespUtils {
    public static String formatRespData(ResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(ResponseEnums.FAILURE.getCode(), ResponseEnums.FAILURE.getMessage(), data));
    }
}
