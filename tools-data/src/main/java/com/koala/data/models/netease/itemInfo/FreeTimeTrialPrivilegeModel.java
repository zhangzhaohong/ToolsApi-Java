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
public class FreeTimeTrialPrivilegeModel implements Serializable {
    private Boolean resConsumable;
    private Boolean userConsumable;
    private Integer type;
    private Integer remainTime;
}