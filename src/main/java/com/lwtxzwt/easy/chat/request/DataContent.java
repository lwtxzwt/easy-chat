package com.lwtxzwt.easy.chat.request;

import java.io.Serializable;

/**
 * Netty Data Content
 * @author lwtxzwt
 * @since 1.0.0
 */
public class DataContent implements Serializable {

    private Integer action;

    private String userId;

    private String receiverId;

    private String msg;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
