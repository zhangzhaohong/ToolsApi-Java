package com.koala.tools.factory.product;

import com.koala.tools.enums.DouYinTypeEnums;
import com.koala.tools.models.douyin.v1.ItemInfoRespModel;
import com.koala.tools.models.douyin.v1.RoomInfoRespModel;
import com.koala.tools.models.xbogus.XbogusDataModel;
import com.koala.tools.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
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
    private Integer itemTypeId = -1;
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

    public void getItemIdByDirectUrl() throws IOException, URISyntaxException {
        if (!Objects.isNull(this.directUrl)) {
            DouYinTypeEnums douYinTypeEnum = DouYinTypeEnums.getEnumsByCode(this.itemTypeId);
            switch (Objects.requireNonNull(douYinTypeEnum)) {
                case NOTE_TYPE, VIDEO_TYPE ->
                        this.itemId = PatternUtil.matchData(douYinTypeEnum.getType() + "(.*?)/", this.directUrl);
                case LIVE_TYPE_1 ->
                        this.itemId = PatternUtil.matchData("https://" + douYinTypeEnum.getType() + "/(.*?)", this.directUrl);
                case LIVE_TYPE_2 -> {
                    String roomId = PatternUtil.matchData(douYinTypeEnum.getPrefix() + "(.*?)\\?", this.directUrl);
                    String liveInfoPath = "https://webcast.amemv.com/webcast/room/reflow/info/?live_id=1&room_id=" + roomId + "&app_id=1128";
                    String roomInfoResponse = doGetXbogusRequest(liveInfoPath);
                    logger.info("[DouYinApiProduct]({}, {}) roomInfoResponse: {}", id, itemId, roomInfoResponse);
                    try {
                        RoomInfoRespModel roomInfo = GsonUtil.toBean(roomInfoResponse, RoomInfoRespModel.class);
                        this.itemId = roomInfo.getData().getRoom().getOwner().getWebRid();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case IMAGE_TYPE, default ->
                        logger.info("[DouYinApiProduct]({}, {}) Unsupported item type id: {}, current url: {}", id, itemId, itemTypeId, this.directUrl);
            }
        }
    }

    public void getItemTypeByDirectUrl() {
        if (!Objects.isNull(this.directUrl)) {
            Arrays.stream(DouYinTypeEnums.values()).forEach(typeEnum -> {
                if (!Objects.isNull(typeEnum.getPrefix()) && this.directUrl.contains(typeEnum.getPrefix())) {
                    this.itemTypeId = typeEnum.getCode();
                }
            });
        }
    }

    public void getRedirectUrl() throws IOException, URISyntaxException {
        if (!Objects.isNull(this.url)) {
            this.directUrl = HttpClientUtil.doGetRedirectLocation(this.url, null, HeaderUtil.getDouYinDownloadHeader());
        }
    }

    public void getItemInfoData() throws IOException, URISyntaxException {
        if (!Objects.isNull(itemId)) {
            switch (Objects.requireNonNull(DouYinTypeEnums.getEnumsByCode(this.itemTypeId))) {
                case VIDEO_TYPE, NOTE_TYPE -> {
                    String itemInfoPath = "https://www.douyin.com/aweme/v1/web/aweme/detail/?aweme_id=" + itemId + "&aid=1128&version_name=23.5.0&device_platform=android&os_version=2333";
                    logger.info("[DouYinApiProduct]({}, {}) itemInfoPath: {}", id, itemId, itemInfoPath);
                    String itemInfoResponse = doGetXbogusRequest(itemInfoPath);
                    logger.info("[DouYinApiProduct]({}, {}) itemInfoResponse: {}", id, itemId, itemInfoResponse);
                    try {
                        this.itemInfo = GsonUtil.toBean(itemInfoResponse, ItemInfoRespModel.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                case IMAGE_TYPE, default ->
                        logger.info("[DouYinApiProduct]({}, {}) Unsupported item type id: {}", id, itemId, itemTypeId);
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
        logger.info("[DouYinApiProduct]({}, {}) params: {url={}, directPath={}, itemTypeId={}}", id, itemId, url, directUrl, itemTypeId);
    }

    public ItemInfoRespModel generateData() {
        switch (Objects.requireNonNull(DouYinTypeEnums.getEnumsByCode(this.itemTypeId))) {
            case NOTE_TYPE -> {
                // do nothing
            }
            case VIDEO_TYPE -> {
                String vid = this.itemInfo.getAwemeDetailModel().getVideo().getPlayAddrInfoModel().getUri();
                String ratio = this.itemInfo.getAwemeDetailModel().getVideo().getRatio();
                if (ObjectUtils.isEmpty(ratio) || Objects.equals(ratio, "default")) {
                    ratio = "540p";
                }
                if (!ObjectUtils.isEmpty(vid)) {
                    String link = "https://aweme.snssdk.com/aweme/v1/play/?video_id=" + vid + "&line=0&ratio=" + ratio + "&media_type=4&vr_type=0&improve_bitrate=0&is_play_url=1&is_support_h265=0&source=PackSourceEnum_PUBLISH";
                    this.itemInfo.getAwemeDetailModel().getVideo().setRealPath(link);
                    this.itemInfo.getAwemeDetailModel().getVideo().setMockPreviewVidPath(host + "tools/DouYin/player/video?vid=" + vid + "&ratio=" + ratio + "&isDownload=0");
                    this.itemInfo.getAwemeDetailModel().getVideo().setMockDownloadVidPath(host + "tools/DouYin/player/video?vid=" + vid + "&ratio=" + ratio + "&isDownload=1");
                    // logger.info("[DouYinApiProduct]({}, {}) realFile: {}", id, itemId,HttpClientUtil.doGetRedirectLocation(link, HeaderUtil.getDouYinDownloadHeader(), null));
                }
            }
            case IMAGE_TYPE, default ->
                    logger.info("[DouYinApiProduct]({}, {}) Unsupported item type id: {}", id, itemId, itemTypeId);
        }
        logger.info("[DouYinApiProduct]({}, {}) itemInfo: {}", id, itemId, this.itemInfo);
        return this.itemInfo;
    }

    private String doGetXbogusRequest(String inputUrl) throws IOException, URISyntaxException {
        XbogusDataModel xbogusDataModel = XbogusUtil.encrypt(inputUrl);
        if (Objects.isNull(xbogusDataModel) || ObjectUtils.isEmpty(xbogusDataModel.getUrl())) {
            logger.error("[DouYinApiProduct]({}, {}) encrypt error, encryptResult: {}", id, itemId, xbogusDataModel);
            throw new NullPointerException("encrypt error");
        }
        logger.info("[DouYinApiProduct]({}, {}) encryptResult: {}", id, itemId, xbogusDataModel);
        return HttpClientUtil.doGet(xbogusDataModel.getUrl(), HeaderUtil.getDouYinSpecialHeader(xbogusDataModel.getMstoken(), xbogusDataModel.getTtwid()), null);
    }

}
