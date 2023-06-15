package com.koala.service.data.database.service.impl;

import com.koala.data.database.models.userData.UserDataTable;
import com.koala.service.data.database.mapper.UserDataMapper;
import com.koala.service.data.database.service.UserDataService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/29 11:25
 * @description
 */
@Service("UserDataService")
public class UserDataServiceImpl implements UserDataService {
    @Resource
    private UserDataMapper userDataMapper;

    @Override
    public Long registerUser(UserDataTable entity) {
        this.insert(entity);
        Long uniqueId = entity.getUniqueId();
        Long userId = Long.parseLong(entity.getUserId().toString() + uniqueId);
        this.updateUserId(userId, uniqueId);
        return userId;
    }

    @Override
    public int insert(UserDataTable entity) {
        return userDataMapper.insert(entity);
    }

    @Override
    public void updateUserId(Long userId, Long uniqueId) {
        userDataMapper.updateUserId(userId, uniqueId);
    }
}
