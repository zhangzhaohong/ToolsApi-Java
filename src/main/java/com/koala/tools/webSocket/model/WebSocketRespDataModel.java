package com.koala.tools.webSocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/31 21:21
 * @description
 */
@Data
@AllArgsConstructor
public class WebSocketRespDataModel<Object extends Serializable> implements Serializable {
    private Integer code;
    private String event;
    private Object data;
}
