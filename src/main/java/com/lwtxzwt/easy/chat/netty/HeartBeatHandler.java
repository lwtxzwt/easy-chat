package com.lwtxzwt.easy.chat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * Heart Beat Handler
 * @author lwtxzwt
 * @since 1.0.0
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;//强制类型转化
            if(event.state()== IdleState.READER_IDLE){
                System.out.println("进入读空闲......");
            }else if(event.state() == IdleState.WRITER_IDLE) {
                System.out.println("进入写空闲......");
            }else if(event.state()== IdleState.ALL_IDLE){
                System.out.println("channel 关闭之前：users 的数量为："+ChatHandler.users.size());
                Channel channel = ctx.channel();
                //资源释放
                channel.close();
                System.out.println("channel 关闭之后：users 的数量为："+ChatHandler.users.size());
            }
        }
    }
}
