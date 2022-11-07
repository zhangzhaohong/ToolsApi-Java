package com.koala.tools.mail;

import com.koala.tools.utils.GsonUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
    private String tmpPath;
    private String taskId;
    private Integer taskIndex;
    private Integer type;
    private String to;
    private String replyTo;
    private String subject;
    private String text;
    private ArrayList<String> fileList;

    public String getReplyTo() {
        if (Objects.isNull(this.replyTo) || StringUtils.isEmpty(this.replyTo)) {
            return null;
        }
        return replyTo;
    }

    public void startExec(EmailSenderService emailService, RedisTemplate<String, Object> redisTemplate) throws MessagingException, UnsupportedEncodingException, Exception {
        Long start = System.currentTimeMillis();
        switch (this.type) {
            case 0:
                emailService.sendMail(this.to, this.getReplyTo(), this.subject, this.text);
                break;
            case 1:
                emailService.sendMailWithAttachment(this.to, this.getReplyTo(), this.subject, this.text, this.fileList);
                break;
            case 2:
                emailService.sendRichMail(this.to, this.getReplyTo(), this.subject, this.text, this.fileList);
                break;
            default:
                break;
        }
        redisTemplate.opsForValue().increment(String.format("task:%s:finished", taskId), 1L);
        redisTemplate.expire(String.format("task:%s:finished", taskId), 12L * 60 * 60, TimeUnit.SECONDS);
        Long end = System.currentTimeMillis();
        if ((end - start) < 2000) {
            log.info("OnWaitInThread: {}, {}, {}", this.taskId, this.taskIndex, 2000 - (end - start) + "ms");
            Thread.sleep(2000 - (end - start));
        }
        log.info("OnSendFinish: {}, {}", GsonUtil.toString(this), (end - start) + "ms");
        Thread.sleep(3L * 1000);
        Object taskLength = redisTemplate.opsForValue().get(String.format("task:length:%s", taskId));
        if (!Objects.isNull(taskLength) && Objects.equals(this.taskIndex, taskLength)) {
            Thread.sleep(5L * 1000);
            log.info("OnSendAllMailFinished: {}", this.taskId);
            try {
                FileSystemUtils.deleteRecursively(new File(String.format("%s/%s", tmpPath, taskId)));
            } catch (Exception e) {
                log.error("结束操作执行失败，请手动清理", e);
            }
        }
    }
}
