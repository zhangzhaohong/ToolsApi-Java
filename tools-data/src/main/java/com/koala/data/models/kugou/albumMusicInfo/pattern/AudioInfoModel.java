package com.koala.data.models.kugou.albumMusicInfo.pattern;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:18
 * @description
 */
@Data
public class AudioInfoModel implements Serializable {
    @SerializedName("is_file_head")
    private String isFileHead;
    @SerializedName("is_file_head_320")
    private String isFileHead320;
    @SerializedName("audio_id")
    private String audioId;
    private String hash;
    private String filesize;
    private String timelength;
    private String bitrate;
    @SerializedName("hash_128")
    private String hash128;
    @SerializedName("filesize_128")
    private String filesize128;
    @SerializedName("timelength_128")
    private String timelength128;
    @SerializedName("hash_320")
    private String hash320;
    @SerializedName("filesize_320")
    private String filesize320;
    @SerializedName("timelength_320")
    private String timelength320;
    @SerializedName("hash_flac")
    private String hashFlac;
    @SerializedName("filesize_flac")
    private String filesizeFlac;
    @SerializedName("timelength_flac")
    private String timelengthFlac;
    @SerializedName("bitrate_flac")
    private String bitrateFlac;
    @SerializedName("hash_high")
    private String hashHigh;
    @SerializedName("filesize_high")
    private String filesizeHigh;
    @SerializedName("timelength_high")
    private String timelengthHigh;
    @SerializedName("bitrate_high")
    private String bitrateHigh;
    @SerializedName("hash_super")
    private String hashSuper;
    @SerializedName("filesize_super")
    private String filesizeSuper;
    @SerializedName("timelength_super")
    private String timelengthSuper;
    @SerializedName("bitrate_super")
    private String bitrateSuper;
    @SerializedName("hash_vinylrecord")
    private String hashVinylrecord;
    @SerializedName("filesize_vinylrecord")
    private String filesizeVinylrecord;
    @SerializedName("timelength_vinylrecord")
    private String timelengthVinylrecord;
    @SerializedName("bitrate_vinylrecord")
    private String bitrateVinylrecord;
    @SerializedName("hash_multichannel")
    private String hashMultichannel;
    @SerializedName("filesize_multichannel")
    private String filesizeMultichannel;
    @SerializedName("timelength_multichannel")
    private String timelengthMultichannel;
    @SerializedName("bitrate_multichannel")
    private String bitrateMultichannel;
    @SerializedName("hash_dolby_448")
    private String hashDolby448;
    @SerializedName("filesize_dolby_448")
    private String filesizeDolby448;
    @SerializedName("timelength_dolby_448")
    private String timelengthDolby448;
    @SerializedName("bitrate_dolby_448")
    private String bitrateDolby448;
    @SerializedName("hash_dolby_640")
    private String hashDolby640;
    @SerializedName("filesize_dolby_640")
    private String filesizeDolby640;
    @SerializedName("timelength_dolby_640")
    private String timelengthDolby640;
    @SerializedName("bitrate_dolby_640")
    private String bitrateDolby640;
    @SerializedName("hash_dolby_768")
    private String hashDolby768;
    @SerializedName("filesize_dolby_768")
    private String filesizeDolby768;
    @SerializedName("timelength_dolby_768")
    private String timelengthDolby768;
    @SerializedName("bitrate_dolby_768")
    private String bitrateDolby768;
    @SerializedName("audio_group_id")
    private String audioGroupId;
    @SerializedName("extname_super")
    private String extnameSuper;
    private String extname;
}