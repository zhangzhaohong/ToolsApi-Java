package com.koala.tools.factory.product;

import com.koala.tools.enums.DouYinTypeEnums;
import com.koala.tools.models.douyin.ItemInfoRespModel;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.utils.HeaderUtil;
import com.koala.tools.utils.HttpClientUtil;
import com.koala.tools.utils.PatternUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.cookie.Cookie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 12:34
 * @description
 */
public class DouYinApiProduct {
    private static final Logger logger = LoggerFactory.getLogger(DouYinApiProduct.class);
    private static final String TICKET_REGISTER_BODY = "{\"region\":\"cn\",\"aid\":1768,\"needFid\":false,\"service\":\"www.ixigua.com\",\"migrate_info\":{\"ticket\":\"\",\"source\":\"node\"},\"cbUrlProtocol\":\"https\",\"union\":true}";
    private String token;
    private String ticket;
    private String url;
    private String host;
    private String directUrl;
    private String id;
    private String itemId;
    private ItemInfoRespModel itemInfo;

    public void init() throws IOException {
        this.token = RandomStringUtils.randomAlphabetic(107);
        List<Cookie> cookieData = HttpClientUtil.doPostJsonAndReturnCookie("https://ttwid.bytedance.com/ttwid/union/register/", TICKET_REGISTER_BODY);
        Optional<Cookie> ticketData = cookieData.stream().filter(item -> "ttwid".equals(item.getName())).findFirst();
        ticketData.ifPresent(cookie -> this.ticket = cookie.getValue());
    }

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
            // String itemInfoPath = "https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=" + itemId;
            String itemInfoPath = "https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id=" + itemId + "&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333&X-Bogus=DFSzswSLj-GANnEftcT4kU9WcBJ/";
            logger.info("[DouYinApiProduct]({}, {}) itemInfoPath: {}", id, itemId, itemInfoPath);
            // String itemInfoResponse = HttpClientUtil.doGet(itemInfoPath, HeaderUtil.getDouYinDownloadHeader(), null);
            String itemInfoResponse = HttpClientUtil.doGet(itemInfoPath, HeaderUtil.getDouYinSpecialHeader(this.token, this.ticket), null);
            logger.info("[DouYinApiProduct]({}, {}) itemInfoResponse: {}", id, itemId, itemInfoResponse);
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
                            item.getVideo().setMockPreviewVidPath(host + "tools/DouYin/player/video?vid=" + vid + "&ratio=" + ratio + "&isDownload=0");
                            item.getVideo().setMockDownloadVidPath(host + "tools/DouYin/player/video?vid=" + vid + "&ratio=" + ratio + "&isDownload=1");
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
