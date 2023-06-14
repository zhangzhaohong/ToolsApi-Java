package com.koala.data.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
import com.koala.data.models.douyin.v1.itemInfo.DiggLottieModel;
import com.koala.data.models.douyin.v1.itemInfo.ImageCommentModel;
import com.koala.data.models.douyin.v1.itemInfo.SeriesPaidInfoModel;
import com.koala.data.models.douyin.v1.itemInfo.VideoGameDataChannelConfigModel;
import com.koala.data.models.douyin.v1.itemInfo.Author;
import com.koala.data.models.douyin.v1.itemInfo.ImageAlbumMusicInfoModel;
import com.koala.data.models.douyin.v1.itemInfo.ImpressionDataModel;
import com.koala.data.models.douyin.v1.itemInfo.VideoTagModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
@Data
public class AwemeMusicDetailModel<Object extends Serializable> implements Serializable {
    @SerializedName("anchor_info")
    private AnchorInfoModel anchorInfo;
    @SerializedName("authentication_token")
    private String authenticationToken;
    private Author author;
    @SerializedName("author_mask_tag")
    private Integer authorMaskTag;
    @SerializedName("author_user_id")
    private Long authorUserId;
    @SerializedName("aweme_control")
    private AwemeControlModel awemeControl;
    @SerializedName("aweme_id")
    private String awemeId;
    @SerializedName("aweme_type")
    private Integer awemeType;
    @SerializedName("book_bar")
    private BookBarModel bookBar;
    @SerializedName("chapter_list")
    private Object chapterList;
    @SerializedName("collect_stat")
    private Integer collectStat;
    @SerializedName("collection_corner_mark")
    private Integer collectionCornerMark;
    @SerializedName("comment_gid")
    private Long commentGid;
    @SerializedName("common_bar_info")
    private String commonBarInfo;
    @SerializedName("component_info_v2")
    private String componentInfoV2;
    @SerializedName("cover_labels")
    private String coverLabels;
    @SerializedName("create_time")
    private Long createTime;
    private String desc;
    private Descendants descendants;
    @SerializedName("digg_lottie")
    private DiggLottieModel diggLottie;
    @SerializedName("disable_relation_bar")
    private Integer disableRelationBar;
    @SerializedName("dislike_dimension_list")
    private String dislikeDimensionList;
    @SerializedName("dislike_dimension_list_v2")
    private String dislikeDimensionListV2;
    @SerializedName("group_id")
    private String groupId;
    @SerializedName("guide_scene_info")
    private GuideSceneInfoModel guideSceneInfo;
    @SerializedName("have_dashboard")
    private Boolean haveDashboard;
    @SerializedName("hot_list")
    private HotListModel hotList;
    @SerializedName("image_album_music_info")
    private ImageAlbumMusicInfoModel imageAlbumMusicInfoModel;
    @SerializedName("image_comment")
    private ImageCommentModel imageComment;
    @SerializedName("image_list")
    private String imageList;
    private String images;
    @SerializedName("img_bitrate")
    private String imgBitrate;
    @SerializedName("impression_data")
    private ImpressionDataModel impressionDataModel;
    @SerializedName("is_collects_selected")
    private Integer isCollectsSelected;
    @SerializedName("is_duet_sing")
    private Boolean isDuetSing;
    @SerializedName("is_first_video")
    private Boolean isFirstVideo;
    @SerializedName("is_image_beat")
    private Boolean isImageBeat;
    @SerializedName("is_karaoke")
    private Boolean isKaraoke;
    @SerializedName("is_life_item")
    private Boolean isLifeItem;
    @SerializedName("is_preview")
    private Integer isPreview;
    @SerializedName("is_share_post")
    private Boolean isSharePost;
    @SerializedName("is_story")
    private Integer isStory;
    @SerializedName("is_top")
    private Integer isTop;
    @SerializedName("item_stitch")
    private Integer itemStitch;
    @SerializedName("item_warn_notification")
    private ItemWarnNotificationModel itemWarnNotification;
    private Music music;
    @SerializedName("origin_text_extra")
    private List<String> originTextExtra;
    @SerializedName("packed_clips")
    private String packedClips;
    @SerializedName("photo_search_entrance")
    private PhotoSearchEntranceModel photoSearchEntrance;
    @SerializedName("poi_patch_info")
    private PoiPatchInfoModel poiPatchInfo;
    @SerializedName("prevent_download")
    private Boolean preventDownload;
    @SerializedName("preview_title")
    private String previewTitle;
    @SerializedName("preview_video_status")
    private Integer previewVideoStatus;
    @SerializedName("ref_tts_id_list")
    private String refTtsIdList;
    @SerializedName("ref_voice_modify_id_list")
    private String refVoiceModifyIdList;
    private String region;
    @SerializedName("relation_labels")
    private String relationLabels;
    @SerializedName("report_action")
    private Boolean reportAction;
    @SerializedName("search_impr")
    private SearchImprModel searchImpr;
    @SerializedName("seo_info")
    private SeoInfoModel seoInfo;
    @SerializedName("series_paid_info")
    private SeriesPaidInfoModel seriesPaidInfo;
    @SerializedName("share_info")
    private ShareInfoModel shareInfo;
    @SerializedName("share_url")
    private String shareUrl;
    @SerializedName("should_open_ad_report")
    private Boolean shouldOpenAdReport;
    private Statistics statistics;
    private Status status;
    @SerializedName("text_extra")
    private List<TextExtraModel> textExtra;
    @SerializedName("tts_id_list")
    private String ttsIdList;
    @SerializedName("user_digged")
    private Integer userDigged;
    @SerializedName("user_recommend_status")
    private Integer userRecommendStatus;
    private Video<?> video;
    @SerializedName("video_game_data_channel_config")
    private VideoGameDataChannelConfigModel videoGameDataChannelConfig;
    @SerializedName("video_tag")
    private List<VideoTagModel> videoTag;
    @SerializedName("voice_modify_id_list")
    private String voiceModifyIdList;
    @SerializedName("without_watermark")
    private Boolean withoutWatermark;
}