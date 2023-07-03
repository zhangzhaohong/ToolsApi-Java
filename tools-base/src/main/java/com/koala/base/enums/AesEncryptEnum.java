package com.koala.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author koala
 * @version 1.0
 * @date 2023/7/3 19:15
 * @description
 */
@Getter
@AllArgsConstructor
public enum AesEncryptEnum {

    CBC("CBC"), ECB("ECB");

    private final String type;

}
