package com.koala.base.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/21 10:45
 * @description
 */
@Getter
public enum KugouRequestQualityEnums {
    QUALITY_DEFAULT("default", "bitrate", "hash", "filesize", "timelength"),
    QUALITY_128("128", null, "hash_128", "filesize_128", "timelength_128"),
    QUALITY_320("320", null, "hash_320", "filesize_320", "timelength_320"),
    QUALITY_FLAC("flac", "bitrate_flac", "hash_flac", "filesize_flac", "timelength_flac"),
    QUALITY_HIGH("high", "bitrate_high", "hash_high", "filesize_high", "timelength_high"),
    QUALITY_SUPER("super", "bitrate_super", "hash_super", "filesize_super", "timelength_super"),
    QUALITY_VINYLRECORD("vinylrecord", "bitrate_vinylrecord", "hash_vinylrecord", "filesize_vinylrecord", "timelength_vinylrecord"),
    QUALITY_MULTICHANNEL("multichannel", "bitrate_multichannel", "hash_multichannel", "filesize_multichannel", "timelength_multichannel"),
    QUALITY_DOLBY_448("dolby_448", "bitrate_dolby_448", "hash_dolby_448", "filesize_dolby_448", "timelength_dolby_448"),
    QUALITY_DOLBY_640("dolby_640", "bitrate_dolby_640", "hash_dolby_640", "filesize_dolby_640", "timelength_dolby_640"),
    QUALITY_DOLBY_768("dolby_768", "bitrate_dolby_768", "hash_dolby_768", "filesize_dolby_768", "timelength_dolby_768");

    private final String type;
    private final String bitrateKey;
    private final String hashKey;
    private final String filesizeKey;
    private final String timelengthKey;

    KugouRequestQualityEnums(String type, String bitrateKey, String hashKey, String filesizeKey, String timelengthKey) {
        this.type = type;
        this.bitrateKey = bitrateKey;
        this.hashKey = hashKey;
        this.filesizeKey = filesizeKey;
        this.timelengthKey = timelengthKey;
    }

    public static KugouRequestQualityEnums getEnumsByType(String type) {
        Optional<KugouRequestQualityEnums> optional = Arrays.stream(KugouRequestQualityEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
