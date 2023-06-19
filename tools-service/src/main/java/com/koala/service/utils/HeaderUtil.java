package com.koala.service.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.koala.service.utils.IpUtil.getRandomIpAddress;

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

    public static Map<String, String> getNeteaseVideoDownloadHeader() {
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

    public static Map<String, String> getNeteaseAudioDownloadHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept", "*/*");
        header.put("Accept-Encoding", "identity;q=1, *;q=0");
        header.put("Accept-Language", "zh-CN,zh;q=0.9,ja;q=0.8,en;q=0.7,zh-TW;q=0.6,de;q=0.5,fr;q=0.4,ca;q=0.3,ga;q=0.2");
        header.put("Range", "bytes=0-");
        header.put("Sec-Fetch-Dest", "audio");
        header.put("Sec-Fetch-Mode", "no-cors");
        header.put("Sec-Fetch-Site", "cross-sit");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getMockVideoHeader(Boolean isDownload) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept-Ranges", "bytes");
        header.put("Expect", "100-continue");
        header.put("Cache-Control", "max-age=604800, must-revalidate");
        header.put("Content-Type", "video/mp4");
        header.put("Content-Disposition", (Boolean.TRUE.equals(isDownload) ? "attachment" : "inline") + "; " + "filename=" + UUID.randomUUID().toString().replace("-", "") + ".mp4");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getMockDownloadNeteaseFileHeader(String fileName, String fileType) {
        final String currentFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Cache-Control", "no-cache");
        header.put("Pragma", "no-cache");
        header.put("Content-Type", "application/octet-stream");
        header.put("Content-Disposition", "attachment; " + "filename=" + currentFileName + "." + fileType);
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getMockLiveStreamHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept-Ranges", "bytes");
        header.put("Expect", "100-continue");
        header.put("Cache-Control", "max-age=604800, must-revalidate");
        header.put("Content-Type", "video/x-flv");
        header.put("Content-Disposition", "inline");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getMockMusicHeader(Boolean isDownload) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("Accept-Ranges", "bytes");
        header.put("Expect", "100-continue");
        header.put("Cache-Control", "max-age=604800, must-revalidate");
        header.put("Content-Type", "audio/mp3");
        header.put("Content-Disposition", (Boolean.TRUE.equals(isDownload) ? "attachment" : "inline") + "; " + "filename=" + UUID.randomUUID().toString().replace("-", "") + ".mp3");
        header.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getDouYinSpecialHeader(String token, String ticket) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        header.put("Accept-Encoding", "None");
        header.put("referer", "https://www.douyin.com/");
        header.put("Cookie", "msToken=" + token + "; ttwid=" + ticket + "; odin_tt=324fb4ea4a89c0c05827e18a1ed9cf9bf8a17f7705fcc793fec935b637867e2a5a9b8168c885554d029919117a18ba69; passport_csrf_token=f61602fc63757ae0e4fd9d6bdcee4810;");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getDouYinWebRequestSpecialHeader(String ticket) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        header.put("Accept-Encoding", "None");
        header.put("referer", "https://www.douyin.com/");
        header.put("Cookie", "ttwid=" + ticket + "; __ac_nonce=0644f93010042b1aedcae; __ac_signature=_02B4Z6wo00f01hyw.YAAAIDD4vyBsOcQreYckPkAAONx9e; __ac_referer=__ac_blank;");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getDouYinTicketGeneratorHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getNeteaseHeader(String cookie) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Safari/537.36 Chrome/91.0.4472.164 NeteaseMusicDesktop/2.10.2.200154");
        header.put("Referer", "");
        header.put("Cookie", cookie);
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getNeteaseDetailHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Safari/537.36 Chrome/91.0.4472.164 NeteaseMusicDesktop/2.10.2.200154");
        header.put("Referer", "");
        header.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getNeteasePublicWithOutCookieHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Safari/537.36 Chrome/91.0.4472.164 NeteaseMusicDesktop/2.10.2.200154");
        header.put("Referer", "");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getKugouPublicWithOutCookieHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", RandomUserAgentGenerator.getUserAgent());
        header.put("Referer", "https://www.kugou.com/");
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

}
