package com.koala.tools.data.dataModel.userData;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/28 21:25
 * @description
 */
@Data
@AllArgsConstructor
@TableName("user_data")
public class UserDataTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long uniqueId;
    private Long userId;
    private String nickName;
    private String password;
    private Integer roleType;
    private String specialRoles;
    private String userToken;
    private Long created;
    private Long updated;
}
