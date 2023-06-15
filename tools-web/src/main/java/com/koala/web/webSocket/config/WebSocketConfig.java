package com.koala.web.webSocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: zhangzhaohong
 * @Date: 2021/05/03/6:46 下午
 * @Description:
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    //CHECKSTYLE:OFF
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        // 在此处设置bufferSize
        container.setMaxTextMessageBufferSize(1024000);
        container.setMaxBinaryMessageBufferSize(1024000);
        container.setMaxSessionIdleTimeout(15 * 60000L);
        return container;
    }
    //CHECKSTYLE:ON
}
