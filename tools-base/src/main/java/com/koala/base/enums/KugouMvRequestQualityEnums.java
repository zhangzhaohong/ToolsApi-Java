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
public enum KugouMvRequestQualityEnums {
    QUALITY_DEFAULT("default", "hd_bitrate", "hd_hash", "hd_filesize", true, "高清"),
    QUALITY_SD_265("sd_265", "sd_bitrate_265", "sd_hash_265", "sd_filesize_265", false, null),
    QUALITY_LD("ld", "ld_bitrate", "ld_hash", "ld_filesize", true, "普清"),
    QUALITY_MKV_SD("mkv_sd", "mkv_sd_bitrate", "mkv_sd_hash", "mkv_sd_filesize", false, null),
    QUALITY_SD("sd", "sd_bitrate", "sd_hash", "sd_filesize", true, "标清"),
    QUALITY_QHD_265("qhd_265", "qhd_bitrate_265", "qhd_hash_265", "qhd_filesize_265", false, null),
    QUALITY_FHD_265("fhd_265", "fhd_bitrate_265", "fhd_hash_265", "fhd_filesize_265", false, null),
    QUALITY_MKV_QHD("mkv_qhd", "mkv_qhd_bitrate", "mkv_qhd_hash", "mkv_qhd_filesize", false, null),
    QUALITY_QHD("qhd", "qhd_bitrate", "qhd_hash", "qhd_filesize", true, "2K"),
    QUALITY_HD_265("hd_265", "hd_bitrate_265", "hd_hash_265", "hd_filesize_265", false, null),
    QUALITY_FHD("fhd", "fhd_bitrate", "fhd_hash", "fhd_filesize", true, "1080P");

    private final String type;
    private final String bitrateKey;
    private final String hashKey;
    private final String filesizeKey;
    private final Boolean canPreview;
    private final String typeName;

    KugouMvRequestQualityEnums(String type, String bitrateKey, String hashKey, String filesizeKey, Boolean canPreview, String typeName) {
        this.type = type;
        this.bitrateKey = bitrateKey;
        this.hashKey = hashKey;
        this.filesizeKey = filesizeKey;
        this.canPreview = canPreview;
        this.typeName = typeName;
    }

    public static KugouMvRequestQualityEnums getEnumsByType(String type) {
        Optional<KugouMvRequestQualityEnums> optional = Arrays.stream(KugouMvRequestQualityEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
