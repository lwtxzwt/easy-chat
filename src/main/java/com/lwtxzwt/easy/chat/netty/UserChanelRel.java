package com.lwtxzwt.easy.chat.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * UserId && Channel Map
 * @author lwtxzwt
 * @since 1.0.0
 */
public class UserChanelRel {

    private static ConcurrentHashMap<String, Channel> manage = new ConcurrentHashMap<>();

    public static  void put(String senderId, Channel channel){
        manage.put(senderId,channel);
    }

    public static Channel get(String senderId){
        return manage.get(senderId);
    }

    public static void output(){
        for (Map.Entry<String,Channel> entry  :manage.entrySet()) {
            System.out.println("UserId:"+entry.getKey()
                    +",ChannelId:"+entry.getValue().id().asLongText()
            );
        }
    }
}
