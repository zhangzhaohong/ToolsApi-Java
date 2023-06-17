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
public class FreeTrialPrivilegeModel implements Serializable {
    private Boolean resConsumable;
    private Boolean userConsumable;
    private String listenType;
    private String cannotListenReason;
}