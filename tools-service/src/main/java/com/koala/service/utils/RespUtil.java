package com.koala.service.utils;

import com.koala.base.enums.*;
import com.koala.data.models.RespModel;

import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 23:35
 * @description
 */
public class RespUtil {

    private RespUtil() {
    }

    public static String formatRespData(PublicResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(PublicResponseEnums.FAILURE.getCode(), PublicResponseEnums.FAILURE.getMessage(), data));
    }


    public static String formatRespData(DouYinResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(DouYinResponseEnums.FAILURE.getCode(), DouYinResponseEnums.FAILURE.getMessage(), data));
    }


    public static String formatRespData(LanZouResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(LanZouResponseEnums.FAILURE.getCode(), LanZouResponseEnums.FAILURE.getMessage(), data));
    }

    public static String formatRespData(NeteaseResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(NeteaseResponseEnums.FAILURE.getCode(), NeteaseResponseEnums.FAILURE.getMessage(), data));
    }

    public static String formatRespData(KugouResponseEnums enums, Object data) {
        if (!Objects.isNull(enums)) {
            return GsonUtil.toString(new RespModel(enums.getCode(), enums.getMessage(), data));
        }
        return GsonUtil.toString(new RespModel(KugouResponseEnums.FAILURE.getCode(), KugouResponseEnums.FAILURE.getMessage(), data));
    }

    public static String formatRespDataWithCustomMsg(Integer code, String msg, Object data) {
        return GsonUtil.toString(new RespModel(code, msg, data));
    }
}
