package com.koala.data.models.kugou.mvInfo.custom;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/21 21:57
 * @description
 */
@Data
@AllArgsConstructor
public class MvInfoModel implements Serializable {
    private String path;
    private String hash;
    private String name;
}
