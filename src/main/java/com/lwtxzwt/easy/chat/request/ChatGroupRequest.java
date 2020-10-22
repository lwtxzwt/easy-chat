package com.lwtxzwt.easy.chat.request;

import java.io.Serializable;

/**
 * Group Chat Request Body
 * @author lwtxzwt
 * @since 1.0.0
 */
public class ChatGroupRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String groupId;

    private String message;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
