package com.koala.tools.mail;

import com.koala.tools.utils.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/22 14:24
 * @description
 */
@Data
@AllArgsConstructor
@Slf4j
public class MailDataContext {
    private String to;
    private String subject;
    private String text;
    private ArrayList<String> fileList;

    public void startExec(EmailService emailService) {
        Long start = System.currentTimeMillis();
        emailService.sendMail(to, subject, text);
        Long end = System.currentTimeMillis();
        log.info("OnSendFinish: {}, {}", GsonUtil.toString(this), (end - start) + "ms");
    }
}
