package com.koala.tools.controller;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.koala.tools.data.dataModel.userData.UserDataTable;
import com.koala.tools.data.service.UserDataService;
import com.koala.tools.handler.CustomMetaObjectHandler;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author koala
 * @version 1.0
 * @date 2023/3/29 11:31
 * @description
 */
@RestController
@RequestMapping("/api/")
public class UserDataController {

    @Resource
    @Qualifier("UserDataService")
    private UserDataService userDataService;

    @GetMapping("/user/test")
    public Integer test() {
        UserDataTable userData = new UserDataTable(null, 1L, "test", "123456", -1, "[]", null, null, null);
        return userDataService.insert(userData);
    }

}
