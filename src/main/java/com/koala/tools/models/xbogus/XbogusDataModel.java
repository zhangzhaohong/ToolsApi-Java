package com.koala.tools.models.xbogus;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/4/9 11:05
 * @description
 */
@Data
public class XbogusDataModel implements Serializable {
    private String xbogus;
    private String mstoken;
    private String ttwid;
    private String url;
}
