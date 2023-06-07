package com.koala.tools.models.douyin;

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
public class MultiQualityInfoModel implements Serializable {
    private String hd;
    private String sd;
}
