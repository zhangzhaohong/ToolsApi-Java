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
@TableName("user_data")
public class UserDataTable implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long uniqueId = null;
    private Long userId;
    private String nickName;
    private String password;
    private Integer roleType;
    private String specialRoles;
    @TableField(fill = FieldFill.INSERT)
    private String userToken = null;
    @TableField(fill = FieldFill.INSERT)
    private Long created = null;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updated = null;

    public UserDataTable(Long userId, String nickName, String password, Integer roleType, String specialRoles) {
        this.userId = userId;
        this.nickName = nickName;
        this.password = password;
        this.roleType = roleType;
        this.specialRoles = specialRoles;
    }
}
