package com.koala.tools.data.dataModel.apiData;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

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
@TableName("api_data")
public class ApiDataTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long uniqueId = null;
    private String path;
    private String data;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Long created;

    public ApiDataTable(String path, String data) {
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.path = path;
        this.data = data;
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.created = timestamp;
    }
}
