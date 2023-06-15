package com.koala.data.models.shortUrl;

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
public class ShortImageDataModel implements Serializable {
    private String title;
    private List<String> data;
}
