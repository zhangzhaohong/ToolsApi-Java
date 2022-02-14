package com.koala.tools.utils;

import java.util.HashMap;
import java.util.Map;

import static com.koala.tools.utils.IpUtil.getRandomIpAddress;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/14 14:44
 * @description
 */
public class HeaderUtil {

    private HeaderUtil() {}

    public static Map<String, String> getHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.8");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getRedirectHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Cache-Control", "no-cache");
        header.put("Connection", "keep-alive");
        header.put("Pragma", "no-cache");
        header.put("Upgrade-Insecure-Requests", "1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getVerifyPasswordHeader(String host) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.9");
        header.put("Referer", host);
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }
}
