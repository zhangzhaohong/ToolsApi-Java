package com.koala.tools.mail;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.koala.tools.models.mail.SendFailedDataModel;
import com.koala.tools.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author koala
 * @version 1.0
 * @date 2022/6/22 13:55
 * @description
 */
@Slf4j
@Service
public class EmailExecutorService implements InitializingBean {

    private final String mailSenderRedisKey = "mail:sender:exec:list";

    private final String mailSenderCancelRedisKey = "mail:sender:exec:list:cancel";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private EmailSenderService emailSenderService;

    private volatile Boolean runLoopFlag = true;

    private int retryTime = 0;

    private final ListeningExecutorService executorService =
            executorService("mail-sender-service", 4, 4);

    public static ListeningExecutorService executorService(String namePrefix, Integer corePoolSize, Integer maxPoolSize) {
        log.info("perf exec executor info = corePoolSize: {}, maxPoolSize: {}", corePoolSize, maxPoolSize);
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.initialize();
        //核心线程池大小
        executor.setCorePoolSize(corePoolSize);
        //缓存区大小
        executor.setQueueCapacity(0);
        //线程池不要太大，防止影响主业务
        executor.setMaxPoolSize(maxPoolSize);
        //存活时间
        executor.setKeepAliveSeconds(30);
        //线程前缀
        executor.setThreadNamePrefix(namePrefix);
        //拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 创建线程池
        return MoreExecutors.listeningDecorator(executor.getThreadPoolExecutor());
    }

    public void addTask(MailDataContext mailDataContext) {
        redisTemplate.opsForList().leftPush(mailSenderRedisKey, GsonUtil.toString(mailDataContext));
    }

    public void cancelTask(String taskId) {
        redisTemplate.opsForSet().add(mailSenderCancelRedisKey, taskId);
    }

    public void scanRedis() {
        while ((Boolean.TRUE.equals(runLoopFlag) || executorService.isTerminated()) && retryTime <= 3) {
            try {
                String result = String.valueOf(redisTemplate.opsForList().rightPop(mailSenderRedisKey));
                retryTime = 0;
                if (Objects.isNull(result) || StringUtils.isEmpty(result) || result.equals("null")) {
                    TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 3));
                    continue;
                }
                MailDataContext mailDataContext = GsonUtil.toBean(result, MailDataContext.class);
                executorService.execute(() -> {
                    if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(mailSenderCancelRedisKey, mailDataContext.getTaskId()))) {
                        log.info("OnSendCancel: {}", GsonUtil.toString(mailDataContext));
                        redisTemplate.opsForValue().increment(String.format("task:%s:canceled", mailDataContext.getTaskId()), 1L);
                        redisTemplate.expire(String.format("task:%s:canceled", mailDataContext.getTaskId()), 12L * 60 * 60, TimeUnit.SECONDS);
                        finalOperation(mailDataContext);
                    } else {
                        try {
                            sendMail(mailDataContext);
                        } catch (Exception e) {
                            log.error("发送失败", e);
                            redisTemplate.opsForList().leftPush(String.format("task:%s:failed", mailDataContext.getTaskId()), GsonUtil.toString(new SendFailedDataModel(mailDataContext.getTaskIndex(), mailDataContext.getTo())));
                            redisTemplate.expire(String.format("task:%s:failed", mailDataContext.getTaskId()), 12L * 60 * 60, TimeUnit.SECONDS);
                            finalOperation(mailDataContext);
                        }
                    }
                });
            } catch (Exception exception) {
                log.error("服务异常", exception);
                retryTime += 1;
                if (retryTime == 3) {
                    this.runLoopFlag = false;
                    log.info("EmailSenderService is stopped!");
                }
            }
        }
    }

    /**
     * finished + 1
     * clean folder
     */
    private void finalOperation(MailDataContext mailDataContext) {
        redisTemplate.opsForValue().increment(String.format("task:%s:finished", mailDataContext.getTaskId()), 1L);
        redisTemplate.expire(String.format("task:%s:finished", mailDataContext.getTaskId()), 12L * 60 * 60, TimeUnit.SECONDS);
        Object taskLength = redisTemplate.opsForValue().get(String.format("task:length:%s", mailDataContext.getTaskId()));
        if (Objects.equals(mailDataContext.getTaskIndex(), taskLength)) {
            try {
                Thread.sleep(5L * 1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            log.info("OnSendAllMailFinished: {}", mailDataContext.getTaskId());
            try {
                FileSystemUtils.deleteRecursively(new File(String.format("%s/%s", mailDataContext.getTmpPath(), mailDataContext.getTaskId())));
            } catch (Exception exception) {
                log.error("结束操作执行失败，请手动清理", exception);
            }
        }
    }

    private void sendMail(MailDataContext mailDataContext) throws Exception {
        mailDataContext.startExec(emailSenderService, redisTemplate);
    }

    public void stopLoop() {
        this.runLoopFlag = false;
        log.warn("～～～服务停止中～～～");
    }


    @Override
    public void afterPropertiesSet() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 5000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(5), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.submit(this::scanRedis, "EmailSenderService");
        threadPoolExecutor.shutdown();
        Runtime.getRuntime().addShutdownHook(new Thread(this::stopLoop));
    }
}
