package com.koala.tools.models.douyin.v1.roomInfoData;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/22 21:51
 * @description
 */
@Data
public class RoomAuthModel implements Serializable {
    @SerializedName("Chat")
    private Boolean chat;
    @SerializedName("Danmaku")
    private Boolean danmaku;
    @SerializedName("Gift")
    private Boolean gift;
    @SerializedName("LuckMoney")
    private Boolean luckMoney;
    @SerializedName("Digg")
    private Boolean digg;
    @SerializedName("RoomContributor")
    private Boolean roomContributor;
    @SerializedName("Props")
    private Boolean props;
    @SerializedName("UserCard")
    private Boolean userCard;
    @SerializedName("POI")
    private Boolean poi;
    @SerializedName("MoreAnchor")
    private Integer moreAnchor;
    @SerializedName("Banner")
    private Integer banner;
    @SerializedName("Share")
    private Integer share;
    @SerializedName("UserCorner")
    private Integer userCorner;
    @SerializedName("Landscape")
    private Integer landscape;
    @SerializedName("LandscapeChat")
    private Integer landscapeChat;
    @SerializedName("PublicScreen")
    private Integer publicScreen;
    @SerializedName("GiftAnchorMt")
    private Integer giftAnchorMt;
    @SerializedName("RecordScreen")
    private Integer recordScreen;
    @SerializedName("DonationSticker")
    private Integer donationSticker;
    @SerializedName("HourRank")
    private Integer hourRank;
    @SerializedName("CommerceCard")
    private Integer commerceCard;
    @SerializedName("AudioChat")
    private Integer audioChat;
    @SerializedName("DanmakuDefault")
    private Integer danmakuDefault;
    @SerializedName("KtvOrderSong")
    private Integer ktvOrderSong;
    @SerializedName("SelectionAlbum")
    private Integer selectionAlbum;
    @SerializedName("Like")
    private Integer like;
    @SerializedName("MultiplierPlayback")
    private Integer multiplierPlayback;
    @SerializedName("DownloadVideo")
    private Integer downloadVideo;
    @SerializedName("Collect")
    private Integer collect;
    @SerializedName("TimedShutdown")
    private Integer timedShutdown;
    @SerializedName("Seek")
    private Integer seek;
    @SerializedName("Denounce")
    private Integer denounce;
    @SerializedName("Dislike")
    private Integer dislike;
    @SerializedName("OnlyTa")
    private Integer onlyTa;
    @SerializedName("CastScreen")
    private Integer castScreen;
    @SerializedName("CommentWall")
    private Integer commentWall;
    @SerializedName("BulletStyle")
    private Integer bulletStyle;
    @SerializedName("ShowGamePlugin")
    private Integer showGamePlugin;
    @SerializedName("VSGift")
    private Integer vsGift;
    @SerializedName("VSTopic")
    private Integer vsTopic;
    @SerializedName("VSRank")
    private Integer vsRank;
    @SerializedName("AdminCommentWall")
    private Integer adminCommentWall;
    @SerializedName("CommerceComponent")
    private Integer commerceComponent;
    @SerializedName("DouPlus")
    private Integer douPlus;
    @SerializedName("GamePointsPlaying")
    private Integer gamePointsPlaying;
    @SerializedName("Poster")
    private Integer poster;
    @SerializedName("Highlights")
    private Integer highlights;
    @SerializedName("TypingCommentState")
    private Integer typingCommentState;
    @SerializedName("StrokeUpDownGuide")
    private Integer strokeUpDownGuide;
    @SerializedName("UpRightStatsFloatingLayer")
    private Integer upRightStatsFloatingLayer;
    @SerializedName("CastScreenExplicit")
    private Integer castScreenExplicit;
    @SerializedName("Selection")
    private Integer selection;
    @SerializedName("IndustryService")
    private Integer industryService;
    @SerializedName("VerticalRank")
    private Integer verticalRank;
    @SerializedName("EnterEffects")
    private Integer enterEffects;
    @SerializedName("FansClub")
    private Integer fansClub;
    @SerializedName("EmojiOutside")
    private Integer emojiOutside;
    @SerializedName("CanSellTicket")
    private Integer canSellTicket;
    @SerializedName("DouPlusPopularityGem")
    private Integer douPlusPopularityGem;
    @SerializedName("MissionCenter")
    private Integer missionCenter;
    @SerializedName("ExpandScreen")
    private Integer expandScreen;
    @SerializedName("FansGroup")
    private Integer fansGroup;
    @SerializedName("Topic")
    private Integer topic;
    @SerializedName("AnchorMission")
    private Integer anchorMission;
    @SerializedName("Teleprompter")
    private Integer teleprompter;
    @SerializedName("SpecialStyle")
    private SpecialStyle specialStyle;
    @SerializedName("FixedChat")
    private Integer fixedChat;
}