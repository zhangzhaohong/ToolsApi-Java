package com.koala.data.models.douyin.v1.musicInfo;

import com.google.gson.annotations.SerializedName;
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
public class Author<Object extends Serializable> implements Serializable {
    @SerializedName("accept_private_policy")
    private Boolean acceptPrivatePolicy;
    @SerializedName("account_region")
    private String accountRegion;
    @SerializedName("apple_account")
    private Integer appleAccount;
    @SerializedName("avatar_larger")
    private AvatarThumbModel avatarLarger;
    @SerializedName("avatar_thumb")
    private AvatarThumbModel avatarThumb;
    @SerializedName("avatar_uri")
    private String avatarUri;
    @SerializedName("aweme_control")
    private AwemeControlModel awemeControl;
    @SerializedName("aweme_count")
    private Integer awemeCount;
    @SerializedName("aweme_hotsoon_auth")
    private Integer awemeHotsoonAuth;
    @SerializedName("ban_user_functions")
    private List<String> banUserFunctions;
    @SerializedName("bind_phone")
    private String bindPhone;
    @SerializedName("can_set_geofencing")
    private String canSetGeofencing;
    @SerializedName("card_entries")
    private String cardEntries;
    @SerializedName("card_entries_not_display")
    private String cardEntriesNotDisplay;
    @SerializedName("card_sort_priority")
    private String cardSortPriority;
    @SerializedName("cf_list")
    private String cfList;
    @SerializedName("close_friend_type")
    private Integer closeFriendType;
    private Integer constellation;
    @SerializedName("contacts_status")
    private Integer contactsStatus;
    @SerializedName("contrail_list")
    private String contrailList;
    @SerializedName("cover_url")
    private List<CoverInfoModel> coverUrl;
    @SerializedName("create_time")
    private Integer createTime;
    @SerializedName("custom_verify")
    private String customVerify;
    @SerializedName("cv_level")
    private String cvLevel;
    @SerializedName("data_label_list")
    private String dataLabelList;
    @SerializedName("display_info")
    private String displayInfo;
    @SerializedName("download_prompt_ts")
    private Integer downloadPromptTs;
    @SerializedName("enable_nearby_visible")
    private Boolean enableNearbyVisible;
    @SerializedName("endorsement_info_list")
    private String endorsementInfoList;
    @SerializedName("enterprise_verify_reason")
    private String enterpriseVerifyReason;
    @SerializedName("familiar_visitor_user")
    private String familiarVisitorUser;
    @SerializedName("favoriting_count")
    private Integer favoritingCount;
    @SerializedName("fb_expire_time")
    private Integer fbExpireTime;
    @SerializedName("follow_status")
    private Integer followStatus;
    @SerializedName("follower_count")
    private Integer followerCount;
    @SerializedName("follower_list_secondary_information_struct")
    private String followerListSecondaryInformationStruct;
    @SerializedName("follower_request_status")
    private Integer followerRequestStatus;
    @SerializedName("follower_status")
    private Integer followerStatus;
    @SerializedName("following_count")
    private Integer followingCount;
    @SerializedName("google_account")
    private String googleAccount;
    @SerializedName("has_email")
    private Boolean hasEmail;
    @SerializedName("has_facebook_token")
    private Boolean hasFacebookToken;
    @SerializedName("has_insights")
    private Boolean hasInsights;
    @SerializedName("has_orders")
    private Boolean hasOrders;
    @SerializedName("has_twitter_token")
    private Boolean hasTwitterToken;
    @SerializedName("has_youtube_token")
    private Boolean hasYoutubeToken;
    @SerializedName("hide_search")
    private Boolean hideSearch;
    @SerializedName("homepage_bottom_toast")
    private String homepageBottomToast;
    @SerializedName("im_role_ids")
    private String imRoleIds;
    @SerializedName("ins_id")
    private String insId;
    @SerializedName("interest_tags")
    private String interestTags;
    @SerializedName("is_binded_weibo")
    private Boolean isBindedWeibo;
    @SerializedName("is_blocked_v2")
    private Boolean isBlockedV2;
    @SerializedName("is_blocking_v2")
    private Boolean isBlockingV2;
    @SerializedName("is_cf")
    private Integer isCf;
    @SerializedName("is_not_show")
    private Boolean isNotShow;
    @SerializedName("is_phone_binded")
    private Boolean isPhoneBinded;
    @SerializedName("ky_only_predict")
    private Object kyOnlyPredict;
    @SerializedName("link_item_list")
    private String linkItemList;
    @SerializedName("live_agreement")
    private Integer liveAgreement;
    @SerializedName("live_agreement_time")
    private Integer liveAgreementTime;
    @SerializedName("live_commerce")
    private Boolean liveCommerce;
    @SerializedName("live_verify")
    private Integer liveVerify;
    @SerializedName("max_follower_count")
    private Integer maxFollowerCount;
    @SerializedName("need_points")
    private String needPoints;
    @SerializedName("need_recommend")
    private Integer needRecommend;
    @SerializedName("neiguang_shield")
    private Integer neiguangShield;
    private String nickname;
    @SerializedName("not_seen_item_id_list")
    private String notSeenItemIdList;
    @SerializedName("not_seen_item_id_list_v2")
    private String notSeenItemIdListV2;
    @SerializedName("offline_info_list")
    private String offlineInfoList;
    @SerializedName("personal_tag_list")
    private String personalTagList;
    @SerializedName("prevent_download")
    private Boolean preventDownload;
    @SerializedName("react_setting")
    private Integer reactSetting;
    @SerializedName("reflow_page_gid")
    private Integer reflowPageGid;
    @SerializedName("reflow_page_uid")
    private Integer reflowPageUid;
    @SerializedName("risk_notice_text")
    private String riskNoticeText;
    @SerializedName("school_category")
    private Integer schoolCategory;
    @SerializedName("school_id")
    private String schoolId;
    @SerializedName("search_impr")
    private SearchImprModel searchImpr;
    @SerializedName("sec_uid")
    private String secUid;
    @SerializedName("share_info")
    private ShareInfoModel shareInfo;
    @SerializedName("share_qrcode_uri")
    private String shareQrcodeUri;
    @SerializedName("shield_comment_notice")
    private Integer shieldCommentNotice;
    @SerializedName("shield_digg_notice")
    private Integer shieldDiggNotice;
    @SerializedName("shield_follow_notice")
    private Integer shieldFollowNotice;
    @SerializedName("show_image_bubble")
    private Boolean showImageBubble;
    @SerializedName("show_nearby_active")
    private Boolean showNearbyActive;
    @SerializedName("signature_extra")
    private String signatureExtra;
    @SerializedName("special_follow_status")
    private Integer specialFollowStatus;
    @SerializedName("special_lock")
    private Integer specialLock;
    @SerializedName("special_people_labels")
    private String specialPeopleLabels;
    private Integer status;
    @SerializedName("stitch_setting")
    private Integer stitchSetting;
    @SerializedName("story_open")
    private Boolean storyOpen;
    @SerializedName("text_extra")
    private String textExtra;
    @SerializedName("total_favorited")
    private Integer totalFavorited;
    @SerializedName("tw_expire_time")
    private Integer twExpireTime;
    @SerializedName("twitter_id")
    private String twitterId;
    @SerializedName("twitter_name")
    private String twitterName;
    private String uid;
    @SerializedName("unique_id_modify_time")
    private Long uniqueIdModifyTime;
    @SerializedName("user_age")
    private Integer userAge;
    @SerializedName("user_canceled")
    private Boolean userCanceled;
    @SerializedName("user_mode")
    private Integer userMode;
    @SerializedName("user_not_see")
    private Integer userNotSee;
    @SerializedName("user_not_show")
    private Integer userNotShow;
    @SerializedName("user_period")
    private Integer userPeriod;
    @SerializedName("user_permissions")
    private String userPermissions;
    @SerializedName("user_rate")
    private Integer userRate;
    @SerializedName("user_tags")
    private String userTags;
    @SerializedName("weibo_name")
    private String weiboName;
    @SerializedName("weibo_schema")
    private String weiboSchema;
    @SerializedName("weibo_url")
    private String weiboUrl;
    @SerializedName("weibo_verify")
    private String weiboVerify;
    @SerializedName("white_cover_url")
    private String whiteCoverUrl;
    @SerializedName("with_dou_entry")
    private Boolean withDouEntry;
    @SerializedName("with_fusion_shop_entry")
    private Boolean withFusionShopEntry;
    @SerializedName("with_shop_entry")
    private Boolean withShopEntry;
    @SerializedName("youtube_channel_id")
    private String youtubeChannelId;
    @SerializedName("youtube_channel_title")
    private String youtubeChannelTitle;
    @SerializedName("youtube_expire_time")
    private Integer youtubeExpireTime;
}