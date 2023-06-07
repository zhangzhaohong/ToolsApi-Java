package com.koala.tools.models.douyin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/7 20:45
 * @description
 */
@Data
@AllArgsConstructor
public class MultiLiveQualityDetailInfoModel implements Serializable {
    private String fullHd1;
    private String hd1;
    private String sd1;
    private String sd2;
}
