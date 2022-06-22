package com.koala.tools.mail;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.koala.tools.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private EmailSenderService emailSenderService;

    private volatile Boolean runLoopFlag = true;

    private final ListeningExecutorService executorService =
            executorService("mail-sender-service", 5, 10);

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

    public void scanRedis() {
        while (Boolean.TRUE.equals(runLoopFlag) || executorService.isTerminated()) {
            try {
                String result = String.valueOf(redisTemplate.opsForList().rightPop(mailSenderRedisKey));
                if (Objects.isNull(result) || StringUtils.isEmpty(result) || result.equals("null")) {
                    TimeUnit.SECONDS.sleep(RandomUtils.nextInt(1, 3));
                    continue;
                }
                MailDataContext mailDataContext = GsonUtil.toBean(result, MailDataContext.class);
                executorService.execute(() -> {
                    try {
                        sendMail(mailDataContext);
                    } catch (Exception e) {
                        log.error("发送失败", e);
                    }
                });
            } catch (Exception e) {
                log.error("服务异常", e);
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
