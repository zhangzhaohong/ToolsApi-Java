package com.koala.tools.controller;

import com.koala.tools.data.dataModel.userData.UserDataTable;
import com.koala.tools.data.service.UserDataService;

import javax.annotation.Resource;

import com.koala.tools.http.annotation.MixedHttpRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @GetMapping("/user/register")
    public Long register(@MixedHttpRequest String nickName, @NonNull @MixedHttpRequest String password, @MixedHttpRequest Integer roleType, @MixedHttpRequest String specialRoles, @MixedHttpRequest String email) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        UserDataTable userData = new UserDataTable(Long.parseLong(simpleDateFormat.format(date)), nickName, password, roleType, specialRoles, email);
        return userDataService.registerUser(userData);
    }

}
