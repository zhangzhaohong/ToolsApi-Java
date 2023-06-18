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
public class PrivilegeModel implements Serializable {
    private Long id;
    private Integer fee;
    private Integer payed;
    private Integer st;
    private Long pl;
    private Integer dl;
    private Integer sp;
    private Integer cp;
    private Integer subp;
    private Boolean cs;
    private Long maxbr;
    private Long fl;
    private Boolean toast;
    private Integer flag;
    private Boolean preSell;
    private Long playMaxbr;
    private Long downloadMaxbr;
    private String maxBrLevel;
    private String playMaxBrLevel;
    private String downloadMaxBrLevel;
    private String plLevel;
    private String dlLevel;
    private String flLevel;
    private String rscl;
    private FreeTrialPrivilege freeTrialPrivilege;
    private List<ChargeInfoList> chargeInfoList;
}