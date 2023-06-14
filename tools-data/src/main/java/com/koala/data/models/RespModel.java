package com.koala.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/11 17:54
 * @description
 */
@Data
@AllArgsConstructor
public class RespModel {
    private Integer code;
    private String message;
    private Object data;
}
