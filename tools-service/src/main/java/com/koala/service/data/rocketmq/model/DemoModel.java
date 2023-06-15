package com.koala.service.data.rocketmq.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/1 17:55
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemoModel implements Serializable {
    private Long id;
    private String message;
}
