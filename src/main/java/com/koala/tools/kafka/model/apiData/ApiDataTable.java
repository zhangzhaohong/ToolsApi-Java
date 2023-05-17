package com.koala.tools.kafka.model.apiData;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author koala
 * @version 1.0
 * @date 2023/5/7 21:22
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiDataTable implements Serializable {
    private Integer code;
    private String path;
    private String method;
    private Long cost;
    private String data;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Long created;

    public ApiDataTable(Integer code, String path, String method, Long cost, String data) {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.code = code;
        this.path = path;
        this.method = method;
        this.cost = cost;
        this.data = data;
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.created = timestamp;
    }
}
