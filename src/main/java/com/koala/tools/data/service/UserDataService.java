package com.koala.tools.data.service;

import com.koala.tools.data.dataModel.userData.UserDataTable;
import org.apache.ibatis.annotations.Param;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/28 21:23
 * @description
 */
public interface UserDataService {
    Long registerUser(UserDataTable entity);
    int insert(UserDataTable entity);

    void updateUserId(Long userId, Long uniqueId);
}
