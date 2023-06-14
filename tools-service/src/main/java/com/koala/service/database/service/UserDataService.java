package com.koala.service.database.service;

import com.koala.data.database.models.userData.UserDataTable;

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
