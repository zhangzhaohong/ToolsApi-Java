package com.koala.tools.models.douyin.v1.itemInfo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/27 21:19
 * @description
 */
@Data
@AllArgsConstructor
public class ImageDataModel implements Serializable {
    private List<String> data;
}
