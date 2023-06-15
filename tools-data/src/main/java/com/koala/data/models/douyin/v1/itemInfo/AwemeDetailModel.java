package com.koala.data.models.douyin.v1.itemInfo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/19 20:38
 * @description
 */
@Data
public class AwemeDetailModel<Object extends Serializable> implements Serializable {
    @SerializedName("admire_auth")
    private AdmireAuthModel admireAuthModel;
    private String anchors;
    @SerializedName("authentication_token")
    private String authenticationToken;
    private Author author;
    @SerializedName("author_mask_tag")
    private Integer authorMaskTag;
    @SerializedName("author_user_id")
    private Long authorUserId;
    @SerializedName("aweme_control")
    private AwemeControlModel awemeControlModel;
    @SerializedName("aweme_id")
    private String awemeId;
    @SerializedName("aweme_type")
    private Integer awemeType;
    @SerializedName("book_bar")
    private BookBarModel bookBarModel;
    @SerializedName("challenge_position")
    private String challengePosition;
    @SerializedName("chapter_list")
    private Object chapterList;
    @SerializedName("collect_stat")
    private Integer collectStat;
    @SerializedName("collection_corner_mark")
    private Integer collectionCornerMark;
    @SerializedName("comment_gid")
    private Long commentGid;
    @SerializedName("comment_list")
    private String commentList;
    @SerializedName("comment_permission_info")
    private CommentPermissionInfoModel commentPermissionInfoModel;
    @SerializedName("commerce_config_data")
    private String commerceConfigData;
    @SerializedName("common_bar_info")
    private String commonBarInfo;
    @SerializedName("component_info_v2")
    private String componentInfoV2;
    @SerializedName("cover_labels")
    private String coverLabels;
    @SerializedName("create_time")
    private Long createTime;
    private String desc;
    @SerializedName("digg_lottie")
    private DiggLottieModel diggLottieModel;
    @SerializedName("disable_relation_bar")
    private Integer disableRelationBar;
    @SerializedName("dislike_dimension_list")
    private String dislikeDimensionList;
    @SerializedName("dislike_dimension_list_v2")
    private String dislikeDimensionListV2;
    @SerializedName("duet_aggregate_in_music_tab")
    private Boolean duetAggregateInMusicTab;
    private Integer duration;
    @SerializedName("feed_comment_config")
    private FeedCommentConfigModel feedCommentConfigModel;
    private List<String> geofencing;
    @SerializedName("geofencing_regions")
    private String geofencingRegions;
    @SerializedName("group_id")
    private String groupId;
    @SerializedName("hybrid_label")
    private String hybridLabel;
    @SerializedName("image_album_music_info")
    private ImageAlbumMusicInfoModel imageAlbumMusicInfoModel;
    @SerializedName("image_comment")
    private ImageCommentModel imageCommentModel;
    @SerializedName("image_infos")
    private String imageInfos;
    @SerializedName("image_list")
    private String imageList;
    private Object images;
    @SerializedName("img_bitrate")
    private Object imgBitrate;
    @SerializedName("impression_data")
    private ImpressionDataModel impressionDataModel;
    @SerializedName("interaction_stickers")
    private String interactionStickers;
    @SerializedName("is_ads")
    private Boolean isAds;
    @SerializedName("is_collects_selected")
    private Integer isCollectsSelected;
    @SerializedName("is_duet_sing")
    private Boolean isDuetSing;
    @SerializedName("is_image_beat")
    private Boolean isImageBeat;
    @SerializedName("is_life_item")
    private Boolean isLifeItem;
    @SerializedName("is_share_post")
    private Boolean isSharePost;
    @SerializedName("is_story")
    private Integer isStory;
    @SerializedName("is_top")
    private Integer isTop;
    @SerializedName("item_warn_notification")
    private ItemWarnNotificationModel itemWarnNotificationModel;
    @SerializedName("label_top_text")
    private String labelTopText;
    @SerializedName("long_video")
    private String longVideo;
    private Music music;
    @SerializedName("nickname_position")
    private String nicknamePosition;
    @SerializedName("origin_comment_ids")
    private String originCommentIds;
    @SerializedName("origin_text_extra")
    private List<String> originTextExtra;
    @SerializedName("original_images")
    private String originalImages;
    @SerializedName("packed_clips")
    private String packedClips;
    @SerializedName("photo_search_entrance")
    private PhotoSearchEntranceModel photoSearchEntranceModel;
    private String position;
    @SerializedName("press_panel_info")
    private String pressPanelInfo;
    @SerializedName("preview_title")
    private String previewTitle;
    @SerializedName("preview_video_status")
    private Integer previewVideoStatus;
    private List<String> promotions;
    private Integer rate;
    @SerializedName("ref_tts_id_list")
    private String refTtsIdList;
    @SerializedName("ref_voice_modify_id_list")
    private String refVoiceModifyIdList;
    private String region;
    @SerializedName("relation_labels")
    private String relationLabels;
    @SerializedName("search_impr")
    private SearchImprModel searchImprModel;
    @SerializedName("series_paid_info")
    private SeriesPaidInfoModel seriesPaidInfoModel;
    @SerializedName("share_info")
    private ShareInfoModel shareInfoModel;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("should_open_ad_report")
    private Boolean shouldOpenAdReport;
    @SerializedName("show_follow_button")
    private ShowFollowButtonModel showFollowButtonModel;
    @SerializedName("social_tag_list")
    private String socialTagList;
    @SerializedName("standard_bar_info_list")
    private String standardBarInfoList;
    private Statistics statistics;
    private Status status;
    @SerializedName("sticker_detail")
    private StickerDetailModel stickerDetailModel;
    @SerializedName("suggest_words")
    private SuggestWordsModel suggestWordsModel;
    @SerializedName("text_extra")
    private List<TextExtraModel> textExtras;
    @SerializedName("tts_id_list")
    private String ttsIdList;
    @SerializedName("uniqid_position")
    private String uniqidPosition;
    @SerializedName("user_digged")
    private Integer userDigged;
    @SerializedName("user_recommend_status")
    private Integer userRecommendStatus;
    private Video<?> video;
    @SerializedName("video_game_data_channel_config")
    private VideoGameDataChannelConfigModel videoGameDataChannelConfigModel;
    @SerializedName("video_labels")
    private String videoLabels;
    @SerializedName("video_tag")
    private List<VideoTagModel> videoTags;
    @SerializedName("video_text")
    private List<String> videoText;
    @SerializedName("voice_modify_id_list")
    private String voiceModifyIdList;
    @SerializedName("mock_preview_picture_path")
    private String mockPreviewPicturePath = null;
}