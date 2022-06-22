package com.koala.tools.controller;

import com.google.gson.Gson;
import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.mail.EmailSenderService;
import com.koala.tools.mail.EmailService;
import com.koala.tools.mail.MailDataContext;
import com.koala.tools.models.RespModel;
import com.koala.tools.models.pixiee.ProductInfoModel;
import com.koala.tools.utils.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/20 11:07
 * @description
 */
@RestController
@RequestMapping("tools/pixiee")
public class PixieeController {

    @Resource
    private EmailSenderService emailSenderService;

    @PostMapping(value = "getInfo", produces = {"application/json;charset=utf-8"})
    public String getInfo(@MixedHttpRequest String description, @MixedHttpRequest String material, @MixedHttpRequest String packageInfo, @MixedHttpRequest String pockets, @MixedHttpRequest String type, @MixedHttpRequest String caring) {
        return GsonUtil.toString(new ProductInfoModel(description, material, packageInfo, pockets, type, caring));
    }

    @PostMapping(value = "sendMail", produces = {"application/json;charset=utf-8"})
    public String sendMail(
            @MixedHttpRequest String to,
            @MixedHttpRequest String subject,
            @MixedHttpRequest String text,
            @MixedHttpRequest(required = false) MultipartFile file
    ) {
        String uuid = UUID.randomUUID().toString();
        emailSenderService.addTask(new MailDataContext(uuid, 0L, to, subject, text, null));
        HashMap<String, Object> result = new HashMap<>(0);
        result.put("taskId", uuid);
        result.put("taskLength", 0);
        return GsonUtil.toString(new RespModel(200, "add task success", result));
    }

}
