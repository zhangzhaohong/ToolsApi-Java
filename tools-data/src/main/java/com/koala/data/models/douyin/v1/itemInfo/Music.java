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
public class Music implements Serializable {
    private String album;
    @SerializedName("artist_user_infos")
    private String artistUserInfos;
    private List<String> artists;
    @SerializedName("audition_duration")
    private Integer auditionDuration;
    private String author;
    @SerializedName("author_deleted")
    private Boolean authorDeleted;
    @SerializedName("author_position")
    private String authorPosition;
    @SerializedName("author_status")
    private Integer authorStatus;
    @SerializedName("avatar_large")
    private AvatarThumbModel avatarLarge;
    @SerializedName("avatar_medium")
    private AvatarThumbModel avatarMedium;
    @SerializedName("avatar_thumb")
    private AvatarThumbModel avatarThumb;
    @SerializedName("binded_challenge_id")
    private Long bindedChallengeId;
    @SerializedName("can_background_play")
    private Boolean canBackgroundPlay;
    @SerializedName("collect_stat")
    private Integer collectStat;
    @SerializedName("cover_hd")
    private CoverThumbModel coverHd;
    @SerializedName("cover_large")
    private CoverThumbModel coverLarge;
    @SerializedName("cover_medium")
    private CoverThumbModel coverMedium;
    @SerializedName("cover_thumb")
    private CoverThumbModel coverThumb;
    @SerializedName("dmv_auto_show")
    private Boolean dmvAutoShow;
    @SerializedName("dsp_status")
    private Integer dspStatus;
    private Integer duration;
    @SerializedName("end_time")
    private Integer endTime;
    @SerializedName("external_song_info")
    private List<String> externalSongInfo;
    private String extra;
    private Long id;
    @SerializedName("id_str")
    private String idStr;
    @SerializedName("is_audio_url_with_cookie")
    private Boolean isAudioUrlWithCookie;
    @SerializedName("is_commerce_music")
    private Boolean isCommerceMusic;
    @SerializedName("is_del_video")
    private Boolean isDelVideo;
    @SerializedName("is_matched_metadata")
    private Boolean isMatchedMetadata;
    @SerializedName("is_original")
    private Boolean isOriginal;
    @SerializedName("is_original_sound")
    private Boolean isOriginalSound;
    @SerializedName("is_pgc")
    private Boolean isPgc;
    @SerializedName("is_restricted")
    private Boolean isRestricted;
    @SerializedName("is_video_self_see")
    private Boolean isVideoSelfSee;
    @SerializedName("luna_info")
    private LunaInfoModel lunaInfoModel;
    @SerializedName("lyric_short_position")
    private String lyricShortPosition;
    private String mid;
    @SerializedName("music_chart_ranks")
    private String musicChartRanks;
    @SerializedName("music_collect_count")
    private Integer musicCollectCount;
    @SerializedName("music_cover_atmosphere_color_value")
    private String musicCoverAtmosphereColorValue;
    private MusicImageBeatsModel musicImageBeatsModel;
    @SerializedName("music_status")
    private Integer musicStatus;
    @SerializedName("musician_user_infos")
    private String musicianUserInfos;
    @SerializedName("mute_share")
    private Boolean muteShare;
    @SerializedName("offline_desc")
    private String offlineDesc;
    @SerializedName("owner_handle")
    private String ownerHandle;
    @SerializedName("owner_id")
    private String ownerId;
    @SerializedName("owner_nickname")
    private String ownerNickname;
    @SerializedName("pgc_music_type")
    private Integer pgcMusicType;
    private PlayInfoModel playInfoModel;
    private String position;
    @SerializedName("prevent_download")
    private Boolean preventDownload;
    @SerializedName("prevent_item_download_status")
    private Integer preventItemDownloadStatus;
    @SerializedName("preview_end_time")
    private Integer previewEndTime;
    @SerializedName("preview_start_time")
    private Float previewStartTime;
    @SerializedName("reason_type")
    private Integer reasonType;
    private Boolean redirect;
    @SerializedName("schema_url")
    private String schemaUrl;
    @SerializedName("search_impr")
    private SearchImprModel searchImprModel;
    @SerializedName("sec_uid")
    private String secUid;
    @SerializedName("shoot_duration")
    private Integer shootDuration;
    @SerializedName("source_platform")
    private Integer sourcePlatform;
    @SerializedName("start_time")
    private Integer startTime;
    private Integer status;
    private StrongBeatInfoModel strongBeatInfoModel;
    @SerializedName("tag_list")
    private String tagList;
    private String title;
    @SerializedName("unshelve_countries")
    private String unshelveCountries;
    @SerializedName("user_count")
    private Integer userCount;
    @SerializedName("video_duration")
    private Integer videoDuration;
}