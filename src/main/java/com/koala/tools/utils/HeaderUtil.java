package com.koala.tools.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.koala.tools.utils.IpUtil.getRandomIpAddress;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/14 14:44
 * @description
 */
public class HeaderUtil {

    private HeaderUtil() {
    }

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
        header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        header.put("Accept-Encoding", "gzip, deflate");
        header.put("Accept-Language", "zh-CN,zh;q=0.8");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 6_0 like Mac OS X) AppleWebKit/536.26 (KHTML, like Gecko) Version/6.0 Mobile/10A5376e Safari/8536.25");
        header.put("Referer", host);
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getDouYinDownloadHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept", "*/*");
        header.put("Accept-Encoding", "identity;q=1, *;q=0");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,ja;q=0.8,en;q=0.7,zh-TW;q=0.6,de;q=0.5,fr;q=0.4,ca;q=0.3,ga;q=0.2");
        header.put("Range", "bytes=0-");
        header.put("Sec-Fetch-Dest", "video");
        header.put("Sec-Fetch-Mode", "no-cors");
        header.put("Sec-Fetch-Site", "cross-sit");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getMockVideoHeader(Boolean isDownload) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Content-Type", "video/mp4");
        header.put("Content-Disposition", Boolean.TRUE.equals(isDownload) ? "attachment" : "inline" + "; filename = " + UUID.randomUUID().toString().replace("-", "") + ".mp4");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }
}
