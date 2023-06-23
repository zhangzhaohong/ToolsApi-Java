package com.koala.service.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/14 18:55
 * @description
 */
public class Base64Utils {

    private static final Base64 base64 = new Base64();

    // 编码、解码格式
    private static final String CODE_FORMATE = "UTF-8";

    /**
     * 1. text明文 转 Base64字符串
     *
     * @param src 明文
     * @return Base64 字符串
     */
    public static String encodeToUrlSafeString(byte[] src) {
        if (StringUtils.isBlank(new String(src))) {
            return new String(src);
        }
        return base64.encodeToString(src);
    }

    public static String encode(byte[] src) {
        if (StringUtils.isBlank(new String(src))) {
            return new String(src);
        }
        return base64.encodeToString(src);
    }


    /**
     * 2. text的Base64字符串 转 明文
     *
     * @param base64Str text的Base64字符串
     * @return text明文
     */
    public static byte[] decodeFromUrlSafeString(String base64Str) {
        if (StringUtils.isBlank(base64Str)) {
            try {
                return base64Str.getBytes(CODE_FORMATE);
            } catch (UnsupportedEncodingException e) {
                return new byte[0];
            }
        }
        return base64.decode(base64Str);
    }

    public static String decode(String base64Str) {
        if (StringUtils.isBlank(base64Str)) {
            try {
                return new String(base64Str.getBytes(CODE_FORMATE));
            } catch (UnsupportedEncodingException e) {
                return new String(new byte[0]);
            }
        }
        return new String(base64.decode(base64Str));
    }

}