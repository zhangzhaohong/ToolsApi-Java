package com.koala.factory.product;

import com.koala.service.data.redis.service.RedisService;
import com.koala.service.utils.HeaderUtil;
import com.koala.service.utils.HttpClientUtil;
import com.koala.service.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:10
 * @description
 */
public class KugouApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(KugouApiProduct.class);
    private final static Long EXPIRE_TIME = 12 * 60 * 60L;
    private final static Long DIRECT_EXPIRE_TIME = 3 * 24 * 60 * 60L;
    private String hash;
    private String albumId;
    private Integer version = 4;
    private String url;
    private String host;
    private RedisService redisService;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setVersion(Integer version) {
        this.version = version;
        logger.info("[KugouApiProduct]({}) Switch to version: {}", this.hash, this.version);
    }

    public void setRedis(RedisService redisService) {
        this.redisService = redisService;
    }

    public void prepareItemIdByShareUrl() throws IOException, URISyntaxException {
        if (StringUtils.hasLength(this.url)) {
            String redirectLocation = HttpClientUtil.doGetRedirectLocation(this.url);
            String response = HttpClientUtil.doGet(redirectLocation, HeaderUtil.getKugouPublicHeader(null, null), null);
            if (StringUtils.hasLength(response)) {
                this.hash = PatternUtil.matchData("\"hash\":\"(.*?)\"", response);
                this.albumId = PatternUtil.matchData("\"album_id\":\"(.*?)\"", response);
            }
            logger.info("[KugouApiProduct]({}) prepare item id finished: {hash={}, albumId={}}", this.hash, this.hash, this.albumId);
        }
    }

    public void setHashAndAlbumId(String hash, String albumId) {
        this.hash = hash;
        this.albumId = albumId;
    }
}
