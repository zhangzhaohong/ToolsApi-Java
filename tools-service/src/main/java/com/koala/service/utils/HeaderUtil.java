package com.koala.service.utils;

import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
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

    public static Map<String, String> getKugouMediaDownloadHeader() {
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

    public static Map<String, String> getMockDownloadKugouFileHeader(String fileName, String fileType) {
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

    public static Map<String, String> getDouYinSpecialHeader(String token, String ticket, String cachedTicket, Boolean isLive) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
        header.put("Accept-Encoding", "None");
        header.put("referer", "https://www.douyin.com/");
        if (isLive) {
            header.put("Cookie", "msToken=" + token + "; ttwid=" + ticket + "; odin_tt=324fb4ea4a89c0c05827e18a1ed9cf9bf8a17f7705fcc793fec935b637867e2a5a9b8168c885554d029919117a18ba69; passport_csrf_token=f61602fc63757ae0e4fd9d6bdcee4810;");
        } else {
            if (StringUtils.hasLength(cachedTicket)) {
                ticket = cachedTicket;
            }
            header.put("Cookie", "msToken=" + token + "; ttwid=" + ticket + "; odin_tt=324fb4ea4a89c0c05827e18a1ed9cf9bf8a17f7705fcc793fec935b637867e2a5a9b8168c885554d029919117a18ba69; passport_csrf_token=f61602fc63757ae0e4fd9d6bdcee4810;");
            // header.put("Cookie", "ttwid=" + ticket + "; __live_version__=%221.1.1.2586%22; odin_tt=a77b90afad5db31e86fe004b39c5f35423292023ce7837cde82fd1f7fe54278890ce24dc89e09c8a2e55b1f4904950a7b0fca6b4fbff3b549ba6d55a335373ec; pwa2=%223%7C0%7C0%7C0%22; s_v_web_id=verify_lkagpdq1_IuHpxJyS_q6YH_4AvH_8aNH_zhvGPr95Jrc8; passport_csrf_token=301cf539fb735ab77de7e382b0dd93e5; passport_csrf_token_default=301cf539fb735ab77de7e382b0dd93e5; bd_ticket_guard_client_data=eyJiZC10aWNrZXQtZ3VhcmQtdmVyc2lvbiI6MiwiYmQtdGlja2V0LWd1YXJkLWl0ZXJhdGlvbi12ZXJzaW9uIjoxLCJiZC10aWNrZXQtZ3VhcmQtcmVlLXB1YmxpYy1rZXkiOiJCRXhuWUdqREVBa3ErdjRsT2l3anRIWi9HU2hRNXFseWdJMklLanIxM0orRHozYnA0M2pXc3M3N25CUzdnbE5tTXhHbWU3cldoSE9pdkJvVmNnT2JiWFU9IiwiYmQtdGlja2V0LWd1YXJkLXdlYi12ZXJzaW9uIjoxfQ==; passport_assist_user=CkHJzB17Xsy3FUHyNfX2Dyb8IFKKA_0pu1SKYG0OAT_av3ImQyCbEmGJV7b8MJep4l9MjeCRK1FPY9k9yAkVHbIbvhpICjzS68aPlRjIsUzHLIEM-5jMbp9awcdJnkACni5Nnc_PBm4ljAlEqChbF4nYPpn4xyh4kY2hBvRikmXs0sgQ4fq2DRiJr9ZUIgEDbm8-yw%3D%3D; n_mh=13KNPUKNEzoW3A4J-OLRxfal2zj1GbF-vJUFPs3WSIY; sso_uid_tt=2581aab41d03156c0b7fee9c7e865c6c; sso_uid_tt_ss=2581aab41d03156c0b7fee9c7e865c6c; toutiao_sso_user=b2556b53ed5cee89e947b154b17645f1; toutiao_sso_user_ss=b2556b53ed5cee89e947b154b17645f1; sid_ucp_sso_v1=1.0.0-KDhlZjRhMmJhZGU0OTVmOWM0YzBkMTY5ZGNkZmI4NTFjNTk2ODU5OTkKHwiPluCxqYzbAhC29OKmBhjvMSAMMLDIpZkGOAZA9AcaAmhsIiBiMjU1NmI1M2VkNWNlZTg5ZTk0N2IxNTRiMTc2NDVmMQ; ssid_ucp_sso_v1=1.0.0-KDhlZjRhMmJhZGU0OTVmOWM0YzBkMTY5ZGNkZmI4NTFjNTk2ODU5OTkKHwiPluCxqYzbAhC29OKmBhjvMSAMMLDIpZkGOAZA9AcaAmhsIiBiMjU1NmI1M2VkNWNlZTg5ZTk0N2IxNTRiMTc2NDVmMQ; sid_guard=c1d1ac1d22198149dfc6cac74938b14a%7C1691925046%7C5184000%7CThu%2C+12-Oct-2023+11%3A10%3A46+GMT; uid_tt=7e39a426dac7802b2448fa2266ca1b85; uid_tt_ss=7e39a426dac7802b2448fa2266ca1b85; sid_tt=c1d1ac1d22198149dfc6cac74938b14a; sessionid=c1d1ac1d22198149dfc6cac74938b14a; sessionid_ss=c1d1ac1d22198149dfc6cac74938b14a; sid_ucp_v1=1.0.0-KDc4Y2VkZjIyN2JlMDNhYmNhYTFlYTE5ODM1YzI2YjVlZDNmMGY0N2YKGwiPluCxqYzbAhC29OKmBhjvMSAMOAZA9AdIBBoCbHEiIGMxZDFhYzFkMjIxOTgxNDlkZmM2Y2FjNzQ5MzhiMTRh; ssid_ucp_v1=1.0.0-KDc4Y2VkZjIyN2JlMDNhYmNhYTFlYTE5ODM1YzI2YjVlZDNmMGY0N2YKGwiPluCxqYzbAhC29OKmBhjvMSAMOAZA9AdIBBoCbHEiIGMxZDFhYzFkMjIxOTgxNDlkZmM2Y2FjNzQ5MzhiMTRh; LOGIN_STATUS=1; _bd_ticket_crypt_cookie=861cdca903469f36dd23fc1ecfe847c1; __security_server_data_status=1; store-region=us; store-region-src=uid; d_ticket=28acd5a9c6df4227b13582669694acded6ede; __ac_nonce=064ec4f3a00901157c769; __ac_signature=_02B4Z6wo00f01ve8HKgAAIDD6.-iFWbfM-r3jRgAANkQTCm7UjsJOQlMGY7o-iPsCIAe0kuriDaQ15lHcML.nW.cGNWpSBLUJzdr6s8KHRbqh5ywvupCeAKBEHKKbji7hD1-Z0x3DI-n0KKx34; douyin.com; device_web_cpu_core=16; device_web_memory_size=-1; webcast_local_quality=null; publish_badge_show_info=%220%2C0%2C0%2C1693208382348%22; IsDouyinActive=true; home_can_add_dy_2_desktop=%220%22; strategyABtestKey=%221693208382.387%22; stream_recommend_feed_params=%22%7B%5C%22cookie_enabled%5C%22%3Atrue%2C%5C%22screen_width%5C%22%3A1344%2C%5C%22screen_height%5C%22%3A756%2C%5C%22browser_online%5C%22%3Atrue%2C%5C%22cpu_core_num%5C%22%3A16%2C%5C%22device_memory%5C%22%3A0%2C%5C%22downlink%5C%22%3A%5C%22%5C%22%2C%5C%22effective_type%5C%22%3A%5C%22%5C%22%2C%5C%22round_trip_time%5C%22%3A0%7D%22; VIDEO_FILTER_MEMO_SELECT=%7B%22expireTime%22%3A1693813183367%2C%22type%22%3A1%7D; volume_info=%7B%22isUserMute%22%3Afalse%2C%22isMute%22%3Atrue%2C%22volume%22%3A1%7D; my_rd=1; passport_fe_beating_status=true; msToken=ESPx4FwNhcdEvr36-bmhWde9xupU_c64WeeqvvzqzLCtmEsvGPXhkwsKM8miaoC2w8gWSzNAfqxPEju4w3jzopIFompVSmwemq9-z1F8V-2vLNhTxLlYCUVdXkzNj6zM; download_guide=%221%2F20230828%2F0%22; csrf_session_id=3c194edf7f2cee968b0df65f97a11648; msToken=" + token + "; tt_scid=-i-7N5fAMRj8pGg4drGXbjasutdtD4tzIeqRnm6OJ1LoXRRZGl8FNhORnEuY3id.b3b7;");
        }
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getDouYinFeedSpecialHeader() {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "com.ss.android.ugc.aweme.lite/220 (Linux; U; Android 5.1.1; zh_CN; MT2-L05; Build/LMY47V; Cronet/58.0.2991.0)");
        header.put("Accept-Encoding", "None");
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

    public static Map<String, String> getNeteaseHttpHeader(@Nullable String cookies) {
        HashMap<String, String> header = new HashMap<>(0);
        header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:46.0) Gecko/20100101 Firefox/46.0");
        header.put("Referer", "http://music.163.com");
        header.put("Host", "music.163.com");
        if (StringUtils.hasLength(cookies)) {
            header.put("Cookie", cookies);
        }
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

    public static Map<String, String> getKugouPublicHeader(String userAgent, String cookie) {
        HashMap<String, String> header = new HashMap<>(0);
        if (StringUtils.hasLength(userAgent)) {
            header.put("User-Agent", userAgent);
        } else {
            header.put("User-Agent", RandomUserAgentGenerator.getUserAgent());
        }
        header.put("Referer", "https://www.kugou.com/");
        if (StringUtils.hasLength(cookie)) {
            header.put("Cookie", cookie);
        }
        header.put("X-FORWARDED-FOR", getRandomIpAddress());
        header.put("CLIENT-IP", getRandomIpAddress());
        return header;
    }

}
