package com.lwtxzwt.easy.chat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Chat Controller
 * @author lwtxzwt
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1")
public class ChatController {

    @RequestMapping(value="/chat", method= RequestMethod.GET)
    public Object chat(@RequestParam  String message, @RequestParam String userId){
        WebSocketServer.SendMessage(userId, message);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
