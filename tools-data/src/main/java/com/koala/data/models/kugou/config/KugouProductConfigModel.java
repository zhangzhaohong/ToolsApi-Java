package com.koala.data.models.kugou.config;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/20 20:39
 * @description
 */
@Data
@AllArgsConstructor
public class KugouProductConfigModel implements Serializable {
    private Boolean albumInfo;
    private Boolean albumMusicInfo;
    private Boolean musicInfo;
}
