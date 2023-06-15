package com.koala.data.models.douyin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/7 13:54
 * @description
 */
@Data
@AllArgsConstructor
public class MultiLiveQualityInfoModel implements Serializable {
    private MultiLiveQualityDetailInfoModel flv;
    private MultiLiveQualityDetailInfoModel hls;
}
