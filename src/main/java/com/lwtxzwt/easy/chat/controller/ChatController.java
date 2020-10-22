package com.lwtxzwt.easy.chat.controller;

import com.lwtxzwt.easy.chat.request.ChatGroupRequest;
import com.lwtxzwt.easy.chat.request.ChatSingleRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Chat Controller
 * @author lwtxzwt
 * @since 1.0.0
 */
@RestController
@RequestMapping("/v1/chat")
public class ChatController {

    /**
     * Single Chat
     * @param req
     * @return
     */
    @PostMapping("/single")
    public Object single(@RequestBody ChatSingleRequest req){
        WebSocketServer.SendMessage(req.getUserId(), req.getMessage());
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

//    /**
//     * Group Chat
//     * @param req
//     * @return
//     */
//    @PostMapping("/group")
//    public Object group(@RequestBody ChatGroupRequest req) {
//        WebSocketServer.SendMessage(req.getGroupId(), req.getMessage());
//        return new ResponseEntity<>(true, HttpStatus.OK);
//    }
}
