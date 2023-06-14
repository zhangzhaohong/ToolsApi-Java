package com.koala.service.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/14 18:55
 * @description
 */
public class Base64Utils {

    // Base64 编码与解码
    private static final Base64.Decoder DECODER_64 = Base64.getDecoder();
    private static final Base64.Encoder ENCODER_64 = Base64.getEncoder();

    // dpi越大转换后的图片越清晰，相对转换速度越慢
    private static final Integer DPI = 200;

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
        String encodedToStr = null;
        try {
            encodedToStr = ENCODER_64.encodeToString(src);
        } catch (Exception ignored) {
        }
        return encodedToStr;
    }

    /**
     * 2. text的Base64字符串 转 明文
     *
     * @param base64Str text的Base64字符串
     * @return text明文
     */
    public static String decodeFromUrlSafeString(String base64Str) {
        if (StringUtils.isBlank(base64Str)) {
            return base64Str;
        }
        String byteToText = null;
        try {
            byteToText = new String(DECODER_64.decode(base64Str), CODE_FORMATE);
        } catch (UnsupportedEncodingException ignored) {
        }
        return byteToText;
    }
}