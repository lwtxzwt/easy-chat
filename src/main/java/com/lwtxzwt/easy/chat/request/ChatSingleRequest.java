package com.lwtxzwt.easy.chat.request;

import java.io.Serializable;

/**
 * Single Chat Request Body
 * @author lwtxzwt
 * @since 1.0.0
 */
public class ChatSingleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
