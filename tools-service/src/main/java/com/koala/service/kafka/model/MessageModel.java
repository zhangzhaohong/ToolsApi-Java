package com.koala.service.kafka.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/15 19:25
 * @description
 */
@Data
public class MessageModel<Object extends Serializable> implements Serializable {
    private Long create;
    private String msg;
    private Object data;

    public MessageModel(String msg, Object data) {
        this.create = System.currentTimeMillis();
        this.msg = msg;
        this.data = data;
    }
}
