package com.lwtxzwt.easy.chat.netty;

import com.alibaba.fastjson.JSON;
import com.lwtxzwt.easy.chat.constants.MessageActionConstant;
import com.lwtxzwt.easy.chat.request.DataContent;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Chat Handler
 * @author lwtxzwt
 * @since 1.0.0
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //用于记录和管理所有客户端的channel
    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端所传输的消息
        String content = msg.text();
        System.out.println(content);
        //1.获取客户端发来的消息
        DataContent dataContent = JSON.parseObject(content, DataContent.class);
        Integer action = dataContent.getAction();
        Channel channel =  ctx.channel();
        //2.判断消息类型，根据不同的类型来处理不同的业务
        if(action == MessageActionConstant.CONNECT){
            //2.1 当websocket 第一次open的时候，初始化channel，把用的channel 和 userid 关联起来
            UserChanelRel.put(dataContent.getUserId(), channel);
        } else if(action == MessageActionConstant.CHAT){
            //2.2 聊天类型的消息，把聊天记录保存到数据库，同时标记消息的签收状态[未签收]
            // 发送消息
            Channel receiverChannel = UserChanelRel.get(dataContent.getReceiverId());
            if(receiverChannel ==null){
                //离线用户
            }else{
                //当receiverChannel 不为空的时候，从ChannelGroup 去查找对应的channel 是否存在
                Channel findChanel = users.find(receiverChannel.id());
                if(findChanel!=null){
                    // 用户在线
                    receiverChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(dataContent)));
                }else{
                    //离线用户
                }
            }
        } else if(action == MessageActionConstant.SIGNED){
//            //2.3 签收消息类型，针对具体的消息进行签收，修改数据库中对应消息的签收状态[已签收]
        } else if(action == MessageActionConstant.KEEPALIVE){
            //2.4 心跳类型的消息
            System.out.println("收到来自channel 为【"+channel+"】的心跳包");
        }

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String chanelId = ctx.channel().id().asShortText();
        System.out.println("客户端被移除：channel id 为："+chanelId);
        users.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
        users.remove(ctx.channel());
    }
}

