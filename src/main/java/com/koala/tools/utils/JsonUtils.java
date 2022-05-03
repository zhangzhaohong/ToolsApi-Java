package com.koala.tools.utils;

import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 14:16
 * @description
 */
public class JsonUtils {
    public static boolean isJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }
}