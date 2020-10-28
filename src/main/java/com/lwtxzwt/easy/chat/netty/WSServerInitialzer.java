package com.lwtxzwt.easy.chat.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * Web Socket Server Initialzer
 * @author lwtxzwt
 * @since 1.0.0
 */
public class WSServerInitialzer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(1024*64));
        pipeline.addLast(new IdleStateHandler(8,10,12));

        // Check Heart Beat
        pipeline.addLast(new HeartBeatHandler());
        // Web Socket Server
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        // Chat Service Handler
        pipeline.addLast(new ChatHandler());
    }
}
