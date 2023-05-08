package com.koala.tools.models.statistics;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
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
    private String uuid = UUID.randomUUID().toString().replace("-", "");

    public StatisticsData(String ip) {
        this.ip = ip;
    }
}
