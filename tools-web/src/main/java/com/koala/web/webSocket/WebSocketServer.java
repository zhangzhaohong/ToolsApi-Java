package com.koala.web.webSocket;

import com.koala.web.BeanContext;
import com.koala.service.data.kafka.model.EventTracker.EventTrackerData;
import com.koala.service.data.kafka.model.MessageModel;
import com.koala.service.data.kafka.service.KafkaService;
import com.koala.service.utils.GsonUtil;
import com.koala.web.webSocket.enums.Constants;
import com.koala.web.webSocket.model.EventTrackerDataModel;
import com.koala.web.webSocket.model.WebSocketDataModel;
import com.koala.web.webSocket.model.WebSocketRespDataModel;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.koala.web.webSocket.enums.Constants.HEARTBREAK;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangzhaohong
 * @Date: 2021/05/03/7:25 下午
 * @Description:
 */
//CHECKSTYLE:OFF
@DependsOn(value = {"beanContext"})
@ServerEndpoint("/websocket/v1/connection/{sid}")
@Component
public class WebSocketServer {
    //存放每个客户端对应的MyWebSocket对象
    private static final CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    // 当前在线连接数
    private static int onlineCount = 0;
    private Session session;
    //接受sid
    private String sid = "";
    private final KafkaService kafkaService = (KafkaService) BeanContext.getBean("EventTrackerKafkaService");

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("sid") String sid) throws IOException {
        logger.info(MessageFormat.format("[WebSocketServer] 推送消息到窗口{0}，推送内容:{1}", sid, message));
        for (WebSocketServer item : webSocketSet) {
            try {
                if (item.sid.equals(sid)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                logger.error("[WebSocketServer] sendInfo exception", e);
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }

    /**
     * 链接成功
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("sid") String sid) throws IOException {
        this.session = session;
        webSocketSet.add(this);
        this.sid = sid;
        addOnlineCount();
        logger.info(MessageFormat.format("[WebSocketServer] 有新窗口开始监听:{0},当前在线人数为:{1}", sid, getOnlineCount()));
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        logger.info("[WebSocketServer] 释放的sid为：%s".formatted(sid));
        logger.info("[WebSocketServer] 有一连接关闭！当前在线人数为%d".formatted(getOnlineCount()));
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info(MessageFormat.format("[WebSocketServer] 收到来自窗口{0}的信息:{1}", sid, message.replace("'", "\"")));
        WebSocketDataModel<?> webSocketDataModel = GsonUtil.toBean(message, WebSocketDataModel.class);
        WebSocketRespDataModel<?> respData;
        Constants constant = Constants.getEnumByEvent(webSocketDataModel.getEvent());
        if (Objects.equals(constant, HEARTBREAK)) {
            respData = new WebSocketRespDataModel<>(constant.getCode(), constant.getEvent(), null);
            sendMessage(GsonUtil.toString(respData));
        } else {
            kafkaService.send(new MessageModel<>(null, new EventTrackerData<>(webSocketDataModel.getEvent(), sid, webSocketDataModel.getData())));
        }
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("[WebSocketServer] websocket onError", error);
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    @Scheduled(cron = "*/30 * * * * ?")
    public void sendStatistics() {
        HashMap<String, Object> extra = new HashMap<>();
        extra.put("online", getOnlineCount());
        kafkaService.send(new MessageModel<>(null, new EventTrackerData<>(Constants.WEBSOCKET_STATISTICS.getEvent(), UUID.randomUUID().toString(), new EventTrackerDataModel<>("Websocket_statistics", extra))));
    }

}
