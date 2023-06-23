package com.koala.data.models.kugou.signature;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/9 11:05
 * @description
 */
@Data
public class KugouSignatureDataModel implements Serializable {
    private String signature;
    private String params;
}
