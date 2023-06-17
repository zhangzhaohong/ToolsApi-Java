package com.koala.factory.product;

import com.koala.data.models.douyin.v1.roomInfoData.RoomInfoDataRespModel;
import com.koala.data.models.netease.detailInfo.NeteaseMusicItemDetailInfoRespModel;
import com.koala.data.models.netease.itemInfo.NeteaseMusicItemInfoRespModel;
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
    private static final String NETEASE_DETAIL_SERVER_URL = "https://music.163.com/api/v3/song/detail";
    private static final Random RANDOM = new Random(0);
    private static final String REQUEST_ID = String.valueOf(RANDOM.nextLong(20000000, 30000000));
    private static final String DEVICE_ID = UUID.randomUUID().toString().replace("-", "");
    private static final byte[] AES_KEY = "e82ckenh8dichen8".getBytes(StandardCharsets.UTF_8);
    private String url;
    private String musicId;
    private String servicePath;
    private String level = "hires";
    private String params;
    private String detailPayload;
    private NeteaseMusicItemInfoRespModel itemInfoData = null;
    private NeteaseMusicItemDetailInfoRespModel itemDetailInfoData = null;

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
                String digest = MD5Utils.customMd5(getDigestPayload(this.level));
                this.params = AESUtils.toHexString(Optional.ofNullable(AESUtils.aes256Encode("%s-36cd479b6b5-%s-36cd479b6b5-%s".formatted(this.servicePath, getPayload(this.level), digest), AES_KEY)).orElse(new byte[]{}));
                logger.info("[NeteaseApiProject]({}) params: {}", this.musicId, "%s-36cd479b6b5-%s-36cd479b6b5-%s".formatted(this.servicePath, getPayload(this.level), digest));
                this.detailPayload = getDetailPayload();
                logger.info("[NeteaseApiProject]({}) init finished, data: [digest: {}, params: {}, detail payload: {}], url: {}", this.musicId, digest, this.params, this.detailPayload, this.url);
            } else {
                logger.error("[NeteaseApiProject]({}) Get host error, url: {}", this.musicId, this.url);
            }
        }
    }

    public void getItemInfoData() throws IOException {
        HashMap<String, String> data = new HashMap<>();
        data.put("params", this.params);
        String itemInfoResponse = HttpClientUtil.doPost(NETEASE_SERVER_URL, HeaderUtil.getNeteaseHeader(CookieUtil.getNeteaseCookie()), data);
        logger.info("[NeteaseApiProject]({}) itemInfoResponse: {}", this.musicId, itemInfoResponse);
        try {
            this.itemInfoData = GsonUtil.toBean(itemInfoResponse, NeteaseMusicItemInfoRespModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getItemDetailData() throws IOException {
        HashMap<String, String> data = new HashMap<>();
        data.put("c", this.detailPayload);
        String itemDetailInfoResponse = HttpClientUtil.doPost(NETEASE_DETAIL_SERVER_URL, HeaderUtil.getNeteaseDetailHeader(), data);
        logger.info("[NeteaseApiProject]({}) itemDetailInfoResponse: {}", this.musicId, itemDetailInfoResponse);
        try {
            this.itemDetailInfoData = GsonUtil.toBean(itemDetailInfoResponse, NeteaseMusicItemDetailInfoRespModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPayload(String level) {
        return "{\"ids\": [\"" + this.musicId + "\"], \"level\": \"" + level + "\", \"encodeType\": \"flac\", \"header\": \"{\\\"os\\\": \\\"pc\\\", \\\"appver\\\": \\\"\\\", \\\"osver\\\": \\\"\\\", \\\"deviceId\\\": \\\"" + DEVICE_ID + "\\\", \\\"requestId\\\": \\\"" + REQUEST_ID + "\\\"}\"}";
    }

    private String getDigestPayload(String level) {
        return "nobody" + this.servicePath + "use" + getPayload(level) + "md5forencrypt";
    }

    private String getDetailPayload() {
        return "[{\"id\": " + this.musicId + ", \"v\": 0}]";
    }

}
