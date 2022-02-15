package com.koala.tools.models.lanzou;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/15 12:53
 * @description
 */
@Data
public class FolderDataRespModel implements Serializable {
    private Integer zt;
    private String info;
    private Object text = new ArrayList<FolderFileInfoRespModel>(0);
}
