package com.koala.tools.models.shortUrl;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/28 11:33
 * @description
 */
@Data
@AllArgsConstructor
public class ShortDouYinItemDataModel implements Serializable {
    private String title;
    private String path;
}
