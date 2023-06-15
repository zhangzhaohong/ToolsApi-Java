package com.koala.service.data.kafka.model.apiData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/7 21:22
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiData implements Serializable {
    private Integer code;
    private String path;
    private String method;
    private Long cost;
    private String data;
    private Long created;

    public ApiData(Integer code, String path, String method, Long cost, String data) {
        this.code = code;
        this.path = path;
        this.method = method;
        this.cost = cost;
        this.data = data;
        this.created = System.currentTimeMillis();
    }
}
