package com.lwtxzwt.easy.chat.config;

import com.lwtxzwt.easy.chat.netty.WebSocketServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Netty Server Start
 * @author lwtxzwt
 * @since 1.0.0
 */
@Component
public class NettyServerBooter implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebSocketServer.getInstance().start();
    }
}
