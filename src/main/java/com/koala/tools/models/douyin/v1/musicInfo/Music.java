
package com.koala.tools.models.douyin.v1.musicInfo;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/26 13:29
 * @description
 */
public class Music {

    private String album;
    private String artist_user_infos;
    private List<String> artists;
    private int audition_duration;
    private String author;
    private boolean author_deleted;
    private String author_position;
    private int author_status;
    private Avatar_large avatar_large;
    private Avatar_medium avatar_medium;
    private Avatar_thumb avatar_thumb;
    private int binded_challenge_id;
    private boolean can_background_play;
    private int collect_stat;
    private Cover_hd cover_hd;
    private Cover_large cover_large;
    private Cover_medium cover_medium;
    private Cover_thumb cover_thumb;
    private boolean dmv_auto_show;
    private int dsp_status;
    private int duration;
    private int end_time;
    private List<String> external_song_info;
    private String extra;
    private long id;
    private String id_str;
    private boolean is_audio_url_with_cookie;
    private boolean is_commerce_music;
    private boolean is_del_video;
    private boolean is_matched_metadata;
    private boolean is_original;
    private boolean is_original_sound;
    private boolean is_pgc;
    private boolean is_restricted;
    private boolean is_video_self_see;
    private Luna_info luna_info;
    private String lyric_short_position;
    private Matched_pgc_sound matched_pgc_sound;
    private String mid;
    private String music_chart_ranks;
    private int music_collect_count;
    private String music_cover_atmosphere_color_value;
    private int music_status;
    private String musician_user_infos;
    private boolean mute_share;
    private String offline_desc;
    private String owner_handle;
    private String owner_id;
    private String owner_nickname;
    private int pgc_music_type;
    private Play_url play_url;
    private String position;
    private boolean prevent_download;
    private int prevent_item_download_status;
    private int preview_end_time;
    private int preview_start_time;
    private int reason_type;
    private boolean redirect;
    private String schema_url;
    private Search_impr search_impr;
    private String sec_uid;
    private int shoot_duration;
    private Song song;
    private int source_platform;
    private int start_time;
    private int status;
    private Strong_beat_url strong_beat_url;
    private String tag_list;
    private String title;
    private String unshelve_countries;
    private int user_count;
    private int video_duration;
    public void setAlbum(String album) {
         this.album = album;
     }
     public String getAlbum() {
         return album;
     }

    public void setArtist_user_infos(String artist_user_infos) {
         this.artist_user_infos = artist_user_infos;
     }
     public String getArtist_user_infos() {
         return artist_user_infos;
     }

    public void setArtists(List<String> artists) {
         this.artists = artists;
     }
     public List<String> getArtists() {
         return artists;
     }

    public void setAudition_duration(int audition_duration) {
         this.audition_duration = audition_duration;
     }
     public int getAudition_duration() {
         return audition_duration;
     }

    public void setAuthor(String author) {
         this.author = author;
     }
     public String getAuthor() {
         return author;
     }

    public void setAuthor_deleted(boolean author_deleted) {
         this.author_deleted = author_deleted;
     }
     public boolean getAuthor_deleted() {
         return author_deleted;
     }

    public void setAuthor_position(String author_position) {
         this.author_position = author_position;
     }
     public String getAuthor_position() {
         return author_position;
     }

    public void setAuthor_status(int author_status) {
         this.author_status = author_status;
     }
     public int getAuthor_status() {
         return author_status;
     }

    public void setAvatar_large(Avatar_large avatar_large) {
         this.avatar_large = avatar_large;
     }
     public Avatar_large getAvatar_large() {
         return avatar_large;
     }

    public void setAvatar_medium(Avatar_medium avatar_medium) {
         this.avatar_medium = avatar_medium;
     }
     public Avatar_medium getAvatar_medium() {
         return avatar_medium;
     }

    public void setAvatar_thumb(Avatar_thumb avatar_thumb) {
         this.avatar_thumb = avatar_thumb;
     }
     public Avatar_thumb getAvatar_thumb() {
         return avatar_thumb;
     }

    public void setBinded_challenge_id(int binded_challenge_id) {
         this.binded_challenge_id = binded_challenge_id;
     }
     public int getBinded_challenge_id() {
         return binded_challenge_id;
     }

    public void setCan_background_play(boolean can_background_play) {
         this.can_background_play = can_background_play;
     }
     public boolean getCan_background_play() {
         return can_background_play;
     }

    public void setCollect_stat(int collect_stat) {
         this.collect_stat = collect_stat;
     }
     public int getCollect_stat() {
         return collect_stat;
     }

    public void setCover_hd(Cover_hd cover_hd) {
         this.cover_hd = cover_hd;
     }
     public Cover_hd getCover_hd() {
         return cover_hd;
     }

    public void setCover_large(Cover_large cover_large) {
         this.cover_large = cover_large;
     }
     public Cover_large getCover_large() {
         return cover_large;
     }

    public void setCover_medium(Cover_medium cover_medium) {
         this.cover_medium = cover_medium;
     }
     public Cover_medium getCover_medium() {
         return cover_medium;
     }

    public void setCover_thumb(Cover_thumb cover_thumb) {
         this.cover_thumb = cover_thumb;
     }
     public Cover_thumb getCover_thumb() {
         return cover_thumb;
     }

    public void setDmv_auto_show(boolean dmv_auto_show) {
         this.dmv_auto_show = dmv_auto_show;
     }
     public boolean getDmv_auto_show() {
         return dmv_auto_show;
     }

    public void setDsp_status(int dsp_status) {
         this.dsp_status = dsp_status;
     }
     public int getDsp_status() {
         return dsp_status;
     }

    public void setDuration(int duration) {
         this.duration = duration;
     }
     public int getDuration() {
         return duration;
     }

    public void setEnd_time(int end_time) {
         this.end_time = end_time;
     }
     public int getEnd_time() {
         return end_time;
     }

    public void setExternal_song_info(List<String> external_song_info) {
         this.external_song_info = external_song_info;
     }
     public List<String> getExternal_song_info() {
         return external_song_info;
     }

    public void setExtra(String extra) {
         this.extra = extra;
     }
     public String getExtra() {
         return extra;
     }

    public void setId(long id) {
         this.id = id;
     }
     public long getId() {
         return id;
     }

    public void setId_str(String id_str) {
         this.id_str = id_str;
     }
     public String getId_str() {
         return id_str;
     }

    public void setIs_audio_url_with_cookie(boolean is_audio_url_with_cookie) {
         this.is_audio_url_with_cookie = is_audio_url_with_cookie;
     }
     public boolean getIs_audio_url_with_cookie() {
         return is_audio_url_with_cookie;
     }

    public void setIs_commerce_music(boolean is_commerce_music) {
         this.is_commerce_music = is_commerce_music;
     }
     public boolean getIs_commerce_music() {
         return is_commerce_music;
     }

    public void setIs_del_video(boolean is_del_video) {
         this.is_del_video = is_del_video;
     }
     public boolean getIs_del_video() {
         return is_del_video;
     }

    public void setIs_matched_metadata(boolean is_matched_metadata) {
         this.is_matched_metadata = is_matched_metadata;
     }
     public boolean getIs_matched_metadata() {
         return is_matched_metadata;
     }

    public void setIs_original(boolean is_original) {
         this.is_original = is_original;
     }
     public boolean getIs_original() {
         return is_original;
     }

    public void setIs_original_sound(boolean is_original_sound) {
         this.is_original_sound = is_original_sound;
     }
     public boolean getIs_original_sound() {
         return is_original_sound;
     }

    public void setIs_pgc(boolean is_pgc) {
         this.is_pgc = is_pgc;
     }
     public boolean getIs_pgc() {
         return is_pgc;
     }

    public void setIs_restricted(boolean is_restricted) {
         this.is_restricted = is_restricted;
     }
     public boolean getIs_restricted() {
         return is_restricted;
     }

    public void setIs_video_self_see(boolean is_video_self_see) {
         this.is_video_self_see = is_video_self_see;
     }
     public boolean getIs_video_self_see() {
         return is_video_self_see;
     }

    public void setLuna_info(Luna_info luna_info) {
         this.luna_info = luna_info;
     }
     public Luna_info getLuna_info() {
         return luna_info;
     }

    public void setLyric_short_position(String lyric_short_position) {
         this.lyric_short_position = lyric_short_position;
     }
     public String getLyric_short_position() {
         return lyric_short_position;
     }

    public void setMatched_pgc_sound(Matched_pgc_sound matched_pgc_sound) {
         this.matched_pgc_sound = matched_pgc_sound;
     }
     public Matched_pgc_sound getMatched_pgc_sound() {
         return matched_pgc_sound;
     }

    public void setMid(String mid) {
         this.mid = mid;
     }
     public String getMid() {
         return mid;
     }

    public void setMusic_chart_ranks(String music_chart_ranks) {
         this.music_chart_ranks = music_chart_ranks;
     }
     public String getMusic_chart_ranks() {
         return music_chart_ranks;
     }

    public void setMusic_collect_count(int music_collect_count) {
         this.music_collect_count = music_collect_count;
     }
     public int getMusic_collect_count() {
         return music_collect_count;
     }

    public void setMusic_cover_atmosphere_color_value(String music_cover_atmosphere_color_value) {
         this.music_cover_atmosphere_color_value = music_cover_atmosphere_color_value;
     }
     public String getMusic_cover_atmosphere_color_value() {
         return music_cover_atmosphere_color_value;
     }

    public void setMusic_status(int music_status) {
         this.music_status = music_status;
     }
     public int getMusic_status() {
         return music_status;
     }

    public void setMusician_user_infos(String musician_user_infos) {
         this.musician_user_infos = musician_user_infos;
     }
     public String getMusician_user_infos() {
         return musician_user_infos;
     }

    public void setMute_share(boolean mute_share) {
         this.mute_share = mute_share;
     }
     public boolean getMute_share() {
         return mute_share;
     }

    public void setOffline_desc(String offline_desc) {
         this.offline_desc = offline_desc;
     }
     public String getOffline_desc() {
         return offline_desc;
     }

    public void setOwner_handle(String owner_handle) {
         this.owner_handle = owner_handle;
     }
     public String getOwner_handle() {
         return owner_handle;
     }

    public void setOwner_id(String owner_id) {
         this.owner_id = owner_id;
     }
     public String getOwner_id() {
         return owner_id;
     }

    public void setOwner_nickname(String owner_nickname) {
         this.owner_nickname = owner_nickname;
     }
     public String getOwner_nickname() {
         return owner_nickname;
     }

    public void setPgc_music_type(int pgc_music_type) {
         this.pgc_music_type = pgc_music_type;
     }
     public int getPgc_music_type() {
         return pgc_music_type;
     }

    public void setPlay_url(Play_url play_url) {
         this.play_url = play_url;
     }
     public Play_url getPlay_url() {
         return play_url;
     }

    public void setPosition(String position) {
         this.position = position;
     }
     public String getPosition() {
         return position;
     }

    public void setPrevent_download(boolean prevent_download) {
         this.prevent_download = prevent_download;
     }
     public boolean getPrevent_download() {
         return prevent_download;
     }

    public void setPrevent_item_download_status(int prevent_item_download_status) {
         this.prevent_item_download_status = prevent_item_download_status;
     }
     public int getPrevent_item_download_status() {
         return prevent_item_download_status;
     }

    public void setPreview_end_time(int preview_end_time) {
         this.preview_end_time = preview_end_time;
     }
     public int getPreview_end_time() {
         return preview_end_time;
     }

    public void setPreview_start_time(int preview_start_time) {
         this.preview_start_time = preview_start_time;
     }
     public int getPreview_start_time() {
         return preview_start_time;
     }

    public void setReason_type(int reason_type) {
         this.reason_type = reason_type;
     }
     public int getReason_type() {
         return reason_type;
     }

    public void setRedirect(boolean redirect) {
         this.redirect = redirect;
     }
     public boolean getRedirect() {
         return redirect;
     }

    public void setSchema_url(String schema_url) {
         this.schema_url = schema_url;
     }
     public String getSchema_url() {
         return schema_url;
     }

    public void setSearch_impr(Search_impr search_impr) {
         this.search_impr = search_impr;
     }
     public Search_impr getSearch_impr() {
         return search_impr;
     }

    public void setSec_uid(String sec_uid) {
         this.sec_uid = sec_uid;
     }
     public String getSec_uid() {
         return sec_uid;
     }

    public void setShoot_duration(int shoot_duration) {
         this.shoot_duration = shoot_duration;
     }
     public int getShoot_duration() {
         return shoot_duration;
     }

    public void setSong(Song song) {
         this.song = song;
     }
     public Song getSong() {
         return song;
     }

    public void setSource_platform(int source_platform) {
         this.source_platform = source_platform;
     }
     public int getSource_platform() {
         return source_platform;
     }

    public void setStart_time(int start_time) {
         this.start_time = start_time;
     }
     public int getStart_time() {
         return start_time;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setStrong_beat_url(Strong_beat_url strong_beat_url) {
         this.strong_beat_url = strong_beat_url;
     }
     public Strong_beat_url getStrong_beat_url() {
         return strong_beat_url;
     }

    public void setTag_list(String tag_list) {
         this.tag_list = tag_list;
     }
     public String getTag_list() {
         return tag_list;
     }

    public void setTitle(String title) {
         this.title = title;
     }
     public String getTitle() {
         return title;
     }

    public void setUnshelve_countries(String unshelve_countries) {
         this.unshelve_countries = unshelve_countries;
     }
     public String getUnshelve_countries() {
         return unshelve_countries;
     }

    public void setUser_count(int user_count) {
         this.user_count = user_count;
     }
     public int getUser_count() {
         return user_count;
     }

    public void setVideo_duration(int video_duration) {
         this.video_duration = video_duration;
     }
     public int getVideo_duration() {
         return video_duration;
     }

}