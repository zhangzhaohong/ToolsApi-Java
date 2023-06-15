package com.koala.web.webSocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/15 18:25
 * @description
 */
@Data
@AllArgsConstructor
public class EventTrackerDataModel<Object extends Serializable> implements Serializable {
    private String route;
    private Object extra;
    private final String platform = "ServerEE";
}
