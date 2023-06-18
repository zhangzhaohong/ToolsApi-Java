package com.koala.data.models.statistics;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/7 22:05
 * @description
 */
@Data
public class StatisticsData implements Serializable {
    private String ip;
    private String env;
    private String version;

    public StatisticsData(String ip, String env, String version) {
        this.ip = ip;
        this.env = env;
        this.version = version;
    }

    private final String uuid = UUID.randomUUID().toString().replace("-", "");
}
