package com.koala.tools.controller;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.read.listener.PageReadListener;
import com.koala.tools.http.annotation.MixedHttpRequest;
import com.koala.tools.mail.EmailExecutorService;
import com.koala.tools.mail.MailDataContext;
import com.koala.tools.models.RespModel;
import com.koala.tools.models.mail.UserListModel;
import com.koala.tools.models.pixiee.ProductInfoModel;
import com.koala.tools.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/20 11:07
 * @description
 */
@Slf4j
@RestController
@RequestMapping("tools/pixiee")
public class PixieeController {

    @Resource(name = "getTmpPath")
    private String tmpPath;

    @Resource
    private EmailExecutorService emailExecutorService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping(value = "getInfo", produces = {"application/json;charset=utf-8"})
    public String getInfo(@MixedHttpRequest String description, @MixedHttpRequest String modelInfo, @MixedHttpRequest String material, @MixedHttpRequest String closure, @MixedHttpRequest String neckline, @MixedHttpRequest String sleeve, @MixedHttpRequest String packageInfo, @MixedHttpRequest String pockets, @MixedHttpRequest String type, @MixedHttpRequest String caring) {
        return GsonUtil.toString(new ProductInfoModel(description, modelInfo, material, closure, neckline, sleeve, packageInfo, pockets, type, caring));
    }

    @PostMapping(value = "sendMail", produces = {"application/json;charset=utf-8"})
    public String sendMail(
            @MixedHttpRequest String to,
            @MixedHttpRequest(required = false) String replyTo,
            @MixedHttpRequest String subject,
            @MixedHttpRequest String text,
            @MixedHttpRequest Integer type,
            @MixedHttpRequest(required = false) List<MultipartFile> file
    ) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        ArrayList<String> fileList = new ArrayList<>(0);
        for (MultipartFile multipartFile : file) {
            fileList = save2TmpFile(uuid, multipartFile, fileList);
        }
        emailExecutorService.addTask(new MailDataContext(tmpPath, uuid, 0, type, to, replyTo, subject, text, fileList));
        redisTemplate.opsForValue().set(String.format("task:length:%s", uuid), 1);
        redisTemplate.expire(String.format("task:length:%s", uuid), 12L * 60 * 60, TimeUnit.SECONDS);
        HashMap<String, Object> result = new HashMap<>(0);
        result.put("taskId", uuid);
        result.put("taskLength", 1);
        return GsonUtil.toString(new RespModel(200, "add task success", result));
    }

    @PostMapping(value = "sendMailWithXlsx", produces = {"application/json;charset=utf-8"})
    public String sendMailWithXlsx(
            @MixedHttpRequest MultipartFile userDataFile,
            @MixedHttpRequest(required = false) String replyTo,
            @MixedHttpRequest String subject,
            @MixedHttpRequest String text,
            @MixedHttpRequest Integer type,
            @MixedHttpRequest(required = false) List<MultipartFile> file
    ) {
        AtomicReference<Integer> taskLength = new AtomicReference<>(0);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        ArrayList<String> userDataList = new ArrayList<>(1);
        userDataList = save2TmpFile(uuid, userDataFile, userDataList);
        ArrayList<String> fileList = new ArrayList<>(0);
        for (MultipartFile multipartFile : file) {
            fileList = save2TmpFile(uuid, multipartFile, fileList);
        }
        if (!userDataList.isEmpty()) {
            File userDataOriginFile = new File(userDataList.get(0));
            ArrayList<String> finalFileList = fileList;
            EasyExcelFactory.read(userDataOriginFile, UserListModel.class, new PageReadListener<UserListModel>(dataList -> {
                for (UserListModel userdata : dataList) {
                    Integer tmp = taskLength.updateAndGet(v -> v + 1);
                    emailExecutorService.addTask(new MailDataContext(tmpPath, uuid, tmp, type, userdata.getEmailAddress(), Objects.equals(replyTo, "") ? null : replyTo, subject, text, finalFileList));
                }
            })).sheet("Sheet1").doRead();
        }
        redisTemplate.opsForValue().set(String.format("task:length:%s", uuid), taskLength.get());
        redisTemplate.expire(String.format("task:length:%s", uuid), 12L * 60 * 60, TimeUnit.SECONDS);
        HashMap<String, Object> result = new HashMap<>(0);
        result.put("taskId", uuid);
        result.put("taskLength", taskLength.get());
        return GsonUtil.toString(new RespModel(200, "add task success", result));
    }

    @GetMapping(value = "getStatus", produces = {"application/json;charset=utf-8"})
    public String getStatus(
            @MixedHttpRequest String taskId
    ) {
        HashMap<String, Object> result = new HashMap<>(0);
        result.put("taskId", taskId);
        result.put("failed", redisTemplate.opsForList().range(String.format("task:%s:failed", taskId), 0, -1));
        result.put("canceled", redisTemplate.opsForValue().get(String.format("task:%s:canceled", taskId)));
        result.put("finished", redisTemplate.opsForValue().get(String.format("task:%s:finished", taskId)));
        result.put("taskLength", redisTemplate.opsForValue().get(String.format("task:length:%s", taskId)));
        return GsonUtil.toString(new RespModel(200, "current task status", result));
    }

    @GetMapping(value = "cancelTask", produces = {"application/json;charset=utf-8"})
    public String cancelTask(
            @MixedHttpRequest String taskId
    ) {
        emailExecutorService.cancelTask(taskId);
        HashMap<String, Object> result = new HashMap<>(0);
        result.put("taskId", taskId);
        result.put("failed", redisTemplate.opsForList().range(String.format("task:%s:failed", taskId), 0, -1));
        result.put("finished", redisTemplate.opsForValue().get(String.format("task:%s:finished", taskId)));
        result.put("taskLength", redisTemplate.opsForValue().get(String.format("task:length:%s", taskId)));
        return GsonUtil.toString(new RespModel(200, "on cancel task success", result));
    }

    private ArrayList<String> save2TmpFile(String taskId, MultipartFile file, ArrayList<String> fileList) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return fileList;
        }
        File folder = new File(String.format("%s/%s", tmpPath, taskId));
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        String filePath = String.format("%s/%s/%s", tmpPath, taskId, file.getOriginalFilename());
        File dest = new File(filePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error(e.toString(), e);
        }
        if (!StringUtils.isEmpty(filePath)) {
            fileList.add(filePath);
        }
        return fileList;
    }

}
