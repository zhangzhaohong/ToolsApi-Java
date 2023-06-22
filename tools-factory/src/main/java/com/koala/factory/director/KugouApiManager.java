package com.koala.factory.director;

import com.koala.data.models.kugou.config.KugouProductConfigModel;
import com.koala.factory.builder.KugouApiBuilder;
import com.koala.factory.extra.kugou.KugouCustomParamsUtil;
import com.koala.factory.product.KugouApiProduct;
import com.koala.service.data.redis.service.RedisService;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 18:29
 * @description
 */
public class KugouApiManager {
    private final KugouApiBuilder builder;

    public KugouApiManager(KugouApiBuilder builder) {
        this.builder = builder;
    }

    public KugouApiProduct construct(RedisService redisService, String host, String url, String hash, String albumId, Integer version, KugouCustomParamsUtil customParams, KugouProductConfigModel config) throws IOException, URISyntaxException {
        builder.redis(redisService).host(host).customParams(customParams);
        if (StringUtils.hasLength(hash) && StringUtils.hasLength(albumId)) {
            builder.hashAndAlbumId(hash, albumId);
        } else {
            builder.url(url).prepareItemIdByShareUrl();
        }
        builder.version(version);
        if (config.getAlbumInfo()) {
            builder.getAlbumInfo();
        }
        if (config.getAlbumMusicInfo()) {
            builder.getAlbumMusicInfo();
        }
        if (config.getMusicInfo()) {
            builder.generatePlayInfo();
        }
        if (config.getLyricInfo()) {
            builder.getLyricInfo();
        }
        return builder.getProduct();
    }
}
