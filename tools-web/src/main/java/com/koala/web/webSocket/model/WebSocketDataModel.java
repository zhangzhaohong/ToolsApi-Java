package com.koala.web.webSocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/31 20:29
 * @description
 */
@Data
@AllArgsConstructor
public class WebSocketDataModel<Object extends Serializable> implements Serializable {
    private String event;
    private String sid;
    private Object data;
}
