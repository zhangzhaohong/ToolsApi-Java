package com.koala.tools.webSocket;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koala.tools.utils.GsonUtil;
import com.koala.tools.webSocket.enums.Constants;
import com.koala.tools.webSocket.model.WebSocketDataModel;
import com.koala.tools.webSocket.model.WebSocketRespDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangzhaohong
 * @Date: 2021/05/03/7:25 下午
 * @Description:
 */
//CHECKSTYLE:OFF
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
        switch (constant) {
            case HEARTBREAK -> {
                respData = new WebSocketRespDataModel<>(constant.getCode(), constant.getEvent(), null);
                sendMessage(GsonUtil.toString(respData));
            }
            default -> {
                logger.info("[WebSocketServer] Unsupported event：%s".formatted(webSocketDataModel.getEvent()));
            }
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
}
