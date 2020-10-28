package com.lwtxzwt.easy.chat.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Web Socket Server
 * @author lwtxzwt
 * @since 1.0.0
 */
@Component
public class WebSocketServer {

    @Value("${chat.server.host}")
    private String host;

    @Value("${chat.server.port}")
    private int port;

    private static class SingletionWSServer {
        static final WebSocketServer instance = new WebSocketServer();
    }

    public static WebSocketServer getInstance() {
        return SingletionWSServer.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap server;
    private ChannelFuture future;

    public WebSocketServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        server = new ServerBootstrap();
        server.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WSServerInitialzer());
    }

    @Bean
    public void start() {
        this.future = server.bind(host,port);
        if (future.isSuccess()) {
            System.out.println("Web Socket Server Started!");
        }
    }
}
