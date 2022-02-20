package com.koala.tools.factory.product;

import com.koala.tools.enums.DouYinTypeEnums;
import com.koala.tools.models.douyin.ItemInfoRespModel;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.HeaderUtil;
import com.koala.tools.utils.HttpClientUtil;
import com.koala.tools.utils.PatternUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

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
    private String host;
    private String directUrl;
    private String id;
    private String itemId;
    private ItemInfoRespModel itemInfo;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
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

    public void getItemInfoData() throws IOException, URISyntaxException {
        if (!Objects.isNull(itemId)) {
            String itemInfoPath = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=" + itemId;
            logger.info("[DouYinApiProduct]({}, {}) itemInfoPath: {}}", id, itemId, itemInfoPath);
            String itemInfoResponse = HttpClientUtil.doGet(itemInfoPath, null, HeaderUtil.getDouYinDownloadHeader());
            logger.info("[DouYinApiProduct]({}, {}) itemInfoResponse: {}}", id, itemId, itemInfoResponse);
            try {
                this.itemInfo = GsonUtil.toBean(itemInfoResponse, ItemInfoRespModel.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ItemInfoRespModel getItemInfo() {
        return itemInfo;
    }

    /**
     * 下面是打印log区域
     */
    public void printParams() {
        logger.info("[DouYinApiProduct]({}, {}) params: {url={}, directPath={}}", id, itemId, url, directUrl);
    }

    public ItemInfoRespModel generateData() {
        this.itemInfo.getItemList().forEach(item -> {
            try {
                switch (Objects.requireNonNull(DouYinTypeEnums.getEnumsByCode(item.getAwemeType()))) {
                    case VIDEO_TYPE:
                        String vid = item.getVideo().getVid();
                        String ratio = item.getVideo().getRatio();
                        if (StringUtils.isEmpty(ratio) || Objects.equals(ratio, "default")) {
                            ratio = "540p";
                        }
                        if (!StringUtils.isEmpty(vid)) {
                            String link = "https://aweme.snssdk.com/aweme/v1/play/?video_id=" + vid + "&line=0&ratio=" + ratio + "&media_type=4&vr_type=0&improve_bitrate=0&is_play_url=1&is_support_h265=0&source=PackSourceEnum_PUBLISH";
                            item.getVideo().setRealPath(link);
                            item.getVideo().setMockPath(host + "/tools/DouYin/getVideo?vid=" + vid + "&ratio=" + ratio);
                            // logger.info("[DouYinApiProduct]({}, {}) realFile: {}", id, itemId,HttpClientUtil.doGetRedirectLocation(link, HeaderUtil.getDouYinDownloadHeader(), null));
                        }
                        break;
                    case IMAGE_TYPE:
                        item.setVideo(null);
                        break;
                    default:
                        item.setVideo(null);
                        item.setImages(null);
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        logger.info("[DouYinApiProduct]({}, {}) itemInfo: {}", id, itemId, this.itemInfo);
        return this.itemInfo;
    }
}
