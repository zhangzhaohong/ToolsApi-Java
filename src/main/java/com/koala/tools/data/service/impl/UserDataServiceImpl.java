package com.koala.tools.data.service.impl;

import com.koala.tools.data.dataModel.userData.UserDataTable;
import com.koala.tools.data.mapper.UserDataMapper;
import com.koala.tools.data.service.UserDataService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

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
