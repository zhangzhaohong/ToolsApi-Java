package com.koala.tools.models.mail;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author koala
 * @version 1.0
 * @date 2022/7/5 09:33
 * @description
 */
@Data
@AllArgsConstructor
public class SendFailedDataModel {
    private Integer taskIndex;
    private String to;
}
