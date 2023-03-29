package com.koala.tools.data.service;

import com.koala.tools.data.dataModel.userData.UserDataTable;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/28 21:23
 * @description
 */
public interface UserDataService {
    int insert(UserDataTable entity);
}
