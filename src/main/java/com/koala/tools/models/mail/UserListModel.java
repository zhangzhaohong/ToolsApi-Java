package com.koala.tools.models.mail;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/22 15:48
 * @description
 */
@Data
public class UserListModel {
    @ExcelProperty("Email_Address")
    private String emailAddress;
}
