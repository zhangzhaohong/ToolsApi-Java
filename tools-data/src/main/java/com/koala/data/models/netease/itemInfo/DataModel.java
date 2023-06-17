package com.koala.data.models.netease.itemInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/6/17 10:59
 * @description
 */
@Data
public class DataModel implements Serializable {
    private Long id;
    private String url;
    private Long br;
    private Long size;
    private String md5;
    private Integer code;
    private Integer expi;
    private String type;
    private Integer gain;
    private Integer peak;
    private Integer fee;
    private String uf;
    private Integer payed;
    private Integer flag;
    private Boolean canExtend;
    private String freeTrialInfo;
    private String level;
    private String encodeType;
    private FreeTrialPrivilegeModel freeTrialPrivilegeModel;
    private FreeTimeTrialPrivilegeModel freeTimeTrialPrivilege;
    private Integer urlSource;
    private Integer rightSource;
    private String podcastCtrp;
    private String effectTypes;
    private Long time;
}