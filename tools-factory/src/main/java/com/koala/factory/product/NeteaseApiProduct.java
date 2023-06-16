package com.koala.factory.product;

import com.koala.factory.extra.CookieUtil;
import com.koala.service.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/16 13:53
 * @description
 */
public class NeteaseApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(NeteaseApiProduct.class);
    private static final String NETEASE_SERVER_URL = "https://interface3.music.163.com/eapi/song/enhance/player/url/v1";
    private static final Random random = new Random(0);
    private static final String DEVICE_ID = UUID.randomUUID().toString().replace("-", "");
    private static final byte[] AES_KEY = "e82ckenh8dichen8".getBytes(StandardCharsets.UTF_8);
    private String url;
    private String musicId;
    private String servicePath;
    private String config;
    private String digest;
    private String level = "hires";
    private String params;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLevel(String level) {
        if (StringUtils.hasLength(level)) {
            this.level = level;
        }
    }

    public void getIdByUrl() {
        if (StringUtils.hasLength(this.url)) {
            String id = PatternUtil.matchData("id=(\\d+)", this.url);
            if (StringUtils.hasLength(id)) {
                this.musicId = id.replace("id=", "");
                logger.info("[NeteaseApiProject]({}) Get music id success, url: {}", this.musicId, this.url);
            }
        }
    }

    public void initRequest() {
        if (StringUtils.hasLength(this.url)) {
            String host = PatternUtil.matchFullData("http(s)?://(([\\w-]+\\.)+\\w+(:\\d{1,5})?)", NETEASE_SERVER_URL);
            if (StringUtils.hasLength(host)) {
                this.servicePath = NETEASE_SERVER_URL.replaceFirst(host, "").replaceFirst("/eapi/", "/api/");
                this.config = getConfig();
                this.digest = MD5Utils.md5("nobody%suse%smd5forencrypt".formatted(this.servicePath, this.config));
                this.params = AESUtils.toHexString(Optional.ofNullable(AESUtils.aes256Encode("%s-36cd479b6b5-%s-36cd479b6b5-%s".formatted(this.servicePath, getPayload(this.level, this.config), digest), AES_KEY)).orElse(new byte[]{}));
                logger.info("[NeteaseApiProject]({}) init finished, data: [config: {}, digest: {}, params: {}], url: {}", this.musicId, this.config, this.digest, this.params, this.url);
            } else {
                logger.error("[NeteaseApiProject]({}) Get host error, url: {}", this.musicId, this.url);
            }
        }
    }

    public void getItemInfoData() throws IOException {
        HashMap<String, String> data = new HashMap<>();
        data.put("params", this.params);
        String itemInfoResponse = HttpClientUtil.doPostJson(NETEASE_SERVER_URL, HeaderUtil.getNeteaseHeader(CookieUtil.getNeteaseCookie()), GsonUtil.toString(data));
        logger.info("[NeteaseApiProject]({}) itemInfoResponse: {}", this.musicId, itemInfoResponse);
    }

    private String getConfig() {
        HashMap<String, String> config = new HashMap<>();
        config.put("os", "pc");
        config.put("appver", "");
        config.put("osver", "");
        config.put("deviceId", DEVICE_ID);
        config.put("requestId", String.valueOf(random.nextLong(20000000, 30000000)));
        return GsonUtil.toString(config);
    }

    private String getPayload(String level, String config) {
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("ids", new String[]{musicId});
        payload.put("level", level);
        payload.put("encodeType", "flac");
        payload.put("header", config);
        return GsonUtil.toString(payload);
    }

    private String getCookie() {
        HashMap<String, String> requestCookie = new HashMap<>();
        requestCookie.put("os", "pc");
        requestCookie.put("appver", "");
        requestCookie.put("osver", "");
        requestCookie.put("deviceId", DEVICE_ID);
        StringBuilder stringBuilder = new StringBuilder();
        requestCookie.forEach((key, value) -> {
            stringBuilder.append(key).append("=").append(value).append(";");
        });
        stringBuilder.append(CookieUtil.getNeteaseCookie());
        return stringBuilder.toString().trim();
    }
}
