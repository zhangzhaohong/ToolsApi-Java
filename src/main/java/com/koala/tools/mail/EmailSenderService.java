package com.koala.tools.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/22 13:19
 * @description
 */
@Component
public class EmailSenderService {
    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.properties.nickName}")
    private String fromName;

    /**
     * 发送纯文本邮件.
     *
     * @param to      目标email 地址
     * @param subject 邮件主题
     * @param text    纯文本内容
     */
    public void sendMail(String to, String replyTo, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setReplyTo(Optional.ofNullable(replyTo).orElse(from));
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    /**
     * 发送邮件并携带附件.
     * 请注意 from 、 to 邮件服务器是否限制邮件大小
     *
     * @param to      目标email 地址
     * @param subject 邮件主题
     * @param text    纯文本内容
     * @param fileList    附件的路径 当然你可以改写传入文件
     */
    public void sendMailWithAttachment(String to, String replyTo, String subject, String text, List<String> fileList) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from, fromName);
        helper.setTo(to);
        helper.setReplyTo(Optional.ofNullable(replyTo).orElse(from));
        helper.setSubject(subject);
        helper.setText(text);
        for (String filePath: fileList) {
            File attachment = new File(filePath);
            helper.addAttachment(attachment.getName(), attachment);
        }
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送富文本邮件.
     *
     * @param to       目标email 地址
     * @param subject  邮件主题
     * @param text     纯文本内容
     * @param fileList 附件的路径 当然你可以改写传入文件
     */
    public void sendRichMail(String to, String replyTo, String subject, String text, List<String> fileList) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setReplyTo(Optional.ofNullable(replyTo).orElse(from));
        helper.setSubject(subject);
        helper.setText(text, true);
        // 图片占位写法  如果图片链接写入模板 注释下面这一行
        // helper.addInline("qr", new FileSystemResource(filePath));
        for (String filePath: fileList) {
            File attachment = new File(filePath);
            helper.addAttachment(attachment.getName(), attachment);
        }
        javaMailSender.send(mimeMessage);
    }

}
