package com.lwtxzwt.easy.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocketServer
 * @author lwtxzwt
 * @since 1.0.0
 */
@ServerEndpoint(value = "/ws/asset")
@Component
public class WebSocketServer {

    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

    private static final AtomicInteger onlineCount = new AtomicInteger(0);

    private static ConcurrentHashMap<String, List<Session>> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        String userId = session.getRequestParameterMap().get("userId").get(0);
        List<Session> sessions = sessionMap.getOrDefault(userId, new ArrayList<Session>());
        sessions.add(session);
        sessionMap.put(userId, sessions);
        int cnt = onlineCount.incrementAndGet();
        log.info("userId:{} joined，online：{}", userId, cnt);
    }

    @OnClose
    public void onClose(Session session) {
        String userId = session.getRequestParameterMap().get("userId").get(0);
        sessionMap.remove(userId);
        int cnt = onlineCount.decrementAndGet();
        log.info("userId:{} closed，online：{}", userId, cnt);
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("error：{}，sessionId： {}",error.getMessage(),session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息
     * @param userId
     * @param message
     */
    public static void SendMessage(String userId, String message) {
        try {
            List<Session> sessions = sessionMap.get(userId);
            if ( sessions != null ) {
                for (Session session : sessions) {
                    session.getBasicRemote()
                            .sendText(message);
                }
            }
        } catch (IOException e) {
            log.error("send message error：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}
