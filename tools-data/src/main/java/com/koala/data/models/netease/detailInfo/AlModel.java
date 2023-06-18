package com.koala.data.models.netease.detailInfo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class AlModel implements Serializable {
    private Long id;
    private String name;
    private String picUrl;
    private List<String> tns;
    private String pic_str;
    private Long pic;
}