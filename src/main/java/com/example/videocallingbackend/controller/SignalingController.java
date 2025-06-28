package com.example.videocallingbackend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class SignalingController {

    @MessageMapping("/signal")
    @SendTo("/topic/signal")
    public String signaling(@Payload String message) {
        return message;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public String chat(@Payload String message) {
        return message;
    }

    @MessageMapping("/join")
    @SendTo("/topic/join")
    public String join(@Payload String username, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", username);
        return username;
    }

    @MessageMapping("/leave")
    @SendTo("/topic/leave")
    public String leave(@Payload String username) {
        return username;
    }
} 