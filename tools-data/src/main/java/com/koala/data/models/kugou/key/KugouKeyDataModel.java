package com.koala.data.models.kugou.key;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/9 11:05
 * @description
 */
@Data
public class KugouKeyDataModel implements Serializable {
    private String key;
    private String params;
}
