package com.koala.tools.models.purchase;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2022/4/3 13:52
 * @description
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class PurchaseInfoModel implements Serializable {
    @ExcelProperty("楼栋")
    private Integer department;
    @ExcelProperty("房号")
    private String room;
    @ExcelProperty("产品")
    private String product;
    @ExcelProperty("手机号")
    private String mobileNumber;
}
