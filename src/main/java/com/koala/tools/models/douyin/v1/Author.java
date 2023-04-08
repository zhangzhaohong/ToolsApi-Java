package com.koala.tools.models.douyin.v1;

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
public class Author implements Serializable {
    @SerializedName("avatar_thumb")
    private AvatarThumbModel avatarThumbModel;
    @SerializedName("cf_list")
    private String cfList;
    @SerializedName("close_friend_type")
    private Integer closeFriendType;
    @SerializedName("contacts_status")
    private Integer contactsStatus;
    @SerializedName("contrail_list")
    private String contrailList;
    @SerializedName("cover_url")
    private List<CoverInfoModel> coverItemModelList;
    @SerializedName("create_time")
    private Integer createTime;
    @SerializedName("custom_verify")
    private String customVerify;
    @SerializedName("data_label_list")
    private String dataLabelList;
    @SerializedName("endorsement_info_list")
    private String endorsementInfoList;
    @SerializedName("enterprise_verify_reason")
    private String enterpriseVerifyReason;
    @SerializedName("favoriting_count")
    private Integer favoritingCount;
    @SerializedName("follow_status")
    private Integer followStatus;
    @SerializedName("follower_count")
    private Integer followerCount;
    @SerializedName("follower_list_secondary_information_struct")
    private String followerListSecondaryInformationStruct;
    @SerializedName("follower_status")
    private Integer followerStatus;
    @SerializedName("following_count")
    private Integer followingCount;
    @SerializedName("im_role_ids")
    private String imRoleIds;
    @SerializedName("is_ad_fake")
    private Boolean isAdFake;
    @SerializedName("is_blocked_v2")
    private Boolean isBlockedV2;
    @SerializedName("is_blocking_v2")
    private Boolean isBlockingV2;
    @SerializedName("is_cf")
    private Integer isCf;
    @SerializedName("max_follower_count")
    private Integer maxFollowerCount;
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
    @SerializedName("risk_notice_text")
    private String riskNoticeText;
    @SerializedName("sec_uid")
    private String secUid;
    private Integer secret;
    @SerializedName("share_info")
    private ShareAuthorInfoModel shareInfoModel;
    @SerializedName("short_id")
    private String shortId;
    private String signature;
    @SerializedName("signature_extra")
    private String signatureExtra;
    @SerializedName("special_follow_status")
    private Integer specialFollowStatus;
    @SerializedName("special_people_labels")
    private String specialPeopleLabels;
    private Integer status;
    @SerializedName("text_extra")
    private String textExtra;
    @SerializedName("total_favorited")
    private Long totalFavorited;
    private String uid;
    @SerializedName("unique_id")
    private String uniqueId;
    @SerializedName("user_age")
    private Integer userAge;
    @SerializedName("user_canceled")
    private Boolean userCanceled;
    @SerializedName("user_permissions")
    private String userPermissions;
    @SerializedName("verification_type")
    private Integer verificationType;
}