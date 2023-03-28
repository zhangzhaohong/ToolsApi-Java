package com.koala.tools.data.dataModel.userData;

import com.baomidou.mybatisplus.annotation.TableName;
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
    private Long uniqueId;
    private Long userId;
    private String nickName;
    private String password;
    private String userToken;
    private Integer roleType;
    private String[] specialRoles;
    private Long updateTimestamp;
}
