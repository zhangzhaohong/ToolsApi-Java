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
    QUALITY_DEFAULT("default", "hd_bitrate", "hd_hash", "hd_filesize"),
    QUALITY_SD_265("sd_265", "sd_bitrate_265", "sd_hash_265", "sd_filesize_265"),
    QUALITY_LD("ld", "ld_bitrate", "ld_hash", "ld_filesize"),
    QUALITY_MKV_SD("mkv_sd", "mkv_sd_bitrate", "mkv_sd_hash", "mkv_sd_filesize"),
    QUALITY_SD("sd", "sd_bitrate", "sd_hash", "sd_filesize"),
    QUALITY_QHD_265("qhd_265", "qhd_bitrate_265", "qhd_hash_265", "qhd_filesize_265"),
    QUALITY_FHD_265("fhd_265", "fhd_bitrate_265", "fhd_hash_265", "fhd_filesize_265"),
    QUALITY_MKV_QHD("mkv_qhd", "mkv_qhd_bitrate", "mkv_qhd_hash", "mkv_qhd_filesize"),
    QUALITY_QHD("qhd", "qhd_bitrate", "qhd_hash", "qhd_filesize"),
    QUALITY_HD_265("hd_265", "hd_bitrate_265", "hd_hash_265", "hd_filesize_265"),
    QUALITY_FHD("fhd", "fhd_bitrate", "fhd_hash", "fhd_filesize");

    private final String type;
    private final String bitrateKey;
    private final String hashKey;
    private final String filesizeKey;

    KugouMvRequestQualityEnums(String type, String bitrateKey, String hashKey, String filesizeKey) {
        this.type = type;
        this.bitrateKey = bitrateKey;
        this.hashKey = hashKey;
        this.filesizeKey = filesizeKey;
    }

    public static KugouMvRequestQualityEnums getEnumsByType(String type) {
        Optional<KugouMvRequestQualityEnums> optional = Arrays.stream(KugouMvRequestQualityEnums.values()).filter(item -> item.getType().equals(type)).findFirst();
        return optional.orElse(null);
    }
}
