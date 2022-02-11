package com.koala.tools.models.lanzou;

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
    private int code;
    private String message;
    private Object data;
}
