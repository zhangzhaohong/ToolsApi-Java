package com.koala.tools.factory.product;

import com.koala.tools.utils.HeaderUtil;
import com.koala.tools.utils.HttpClientUtil;
import com.koala.tools.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 12:34
 * @description
 */
public class DouYinApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(DouYinApiProduct.class);
    private String url;
    private String directUrl;
    private String pageData;
    private String id;
    private String itemId;

    public void setUrl(String url) {
        this.url = url;
    }

    public void getIdByUrl() {
        if (!Objects.isNull(this.url)) {
            this.id = PatternUtil.matchData("douyin.com/(.*?)/", this.url);
        }
    }

    public void getItemIdByDirectUrl() {
        if (!Objects.isNull(this.directUrl)) {
            this.itemId = PatternUtil.matchData("video/(.*?)/", this.directUrl);
        }
    }

    public void getRedirectUrl() throws IOException, URISyntaxException {
        if (!Objects.isNull(this.url)) {
            this.directUrl = HttpClientUtil.doGetRedirectLocation(this.url, null, HeaderUtil.getDouYinDownloadHeader());
        }
    }

    /**
     * 下面是打印log区域
     */
    public void printParams() {
        logger.info("[DouYinApiProduct]({}, {}) params: {url={}, directPath={}}", id, itemId, url, directUrl);
    }

    public void printPageData() {
        logger.info("[DouYinApiProduct]({}, {}) pageData: {}}", id, itemId, pageData);
    }
}
