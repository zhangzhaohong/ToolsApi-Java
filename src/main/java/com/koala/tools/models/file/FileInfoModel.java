package com.koala.tools.models.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/2/12 17:34
 * @description
 */
@Data
public class FileInfoModel implements Serializable {
    private String fileName;
    private String fileSize;
    private String updateTime;
    private String author;
    private String pathPrefix;
    private String downloadHost;
    private String downloadPath;
    private String downloadUrl;
}
