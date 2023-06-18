package com.koala.data.models.netease;

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
public class MultiMvQualityInfoModel implements Serializable {
    private String fullHd1;
    private String hd1;
    private String sd1;
    private String sd2;
}
