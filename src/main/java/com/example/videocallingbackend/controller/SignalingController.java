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

    @MessageMapping("/handraise")
    @SendTo("/topic/handraise")
    public String handraise(@Payload String message) {
        return message;
    }

    @MessageMapping("/lowerhand")
    @SendTo("/topic/lowerhand")
    public String lowerhand(@Payload String message) {
        return message;
    }

    @MessageMapping("/mute")
    public void mute(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
        // Extract username from message and send to their personal topic
        // For simplicity, just forward to /user/{username}/topic/mute
        String username = extractUsername(message);
        if (username != null) {
            headerAccessor.getSessionAttributes().put("username", username);
            messagingTemplate.convertAndSendToUser(username, "/topic/mute", "");
        }
    }

    @MessageMapping("/remove")
    public void remove(@Payload String message, SimpMessageHeaderAccessor headerAccessor) {
        String username = extractUsername(message);
        if (username != null) {
            headerAccessor.getSessionAttributes().put("username", username);
            messagingTemplate.convertAndSendToUser(username, "/topic/remove", "");
        }
    }

    @MessageMapping("/lock")
    @SendTo("/topic/lock")
    public String lock(@Payload String message) {
        return message;
    }

    @MessageMapping("/speaking")
    @SendTo("/topic/speaking")
    public String speaking(@Payload String message) {
        return message;
    }

    @MessageMapping("/silent")
    @SendTo("/topic/silent")
    public String silent(@Payload String message) {
        return message;
    }

    // Helper to extract username from JSON string
    private String extractUsername(String message) {
        try {
            int idx = message.indexOf("username");
            if (idx == -1) return null;
            int start = message.indexOf(':', idx) + 1;
            int end = message.indexOf(',', start);
            if (end == -1) end = message.indexOf('}', start);
            String username = message.substring(start, end).replaceAll("[\"{} ]", "");
            return username;
        } catch (Exception e) {
            return null;
        }
    }

    @org.springframework.beans.factory.annotation.Autowired
    private org.springframework.messaging.simp.SimpMessagingTemplate messagingTemplate;
}