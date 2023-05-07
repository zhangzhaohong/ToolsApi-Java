package com.koala.tools.models.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/7 22:05
 * @description
 */
@Data
@AllArgsConstructor
public class StatisticsData implements Serializable {
    private String ip;
}
