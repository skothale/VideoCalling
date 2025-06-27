package com.example.videocallingbackend.controller;

import com.example.videocallingbackend.model.Meeting;
import com.example.videocallingbackend.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/meetings")
@CrossOrigin(origins = {
    "https://localhost:3000", 
    "http://localhost:3000", 
    "https://192.168.1.7:3000",
    "http://192.168.1.7:3000",
    "https://127.0.0.1:3000",
    "http://127.0.0.1:3000"
})
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping("/create")
    public ResponseEntity<Meeting> createMeeting(@RequestParam String host) {
        Meeting meeting = meetingService.createMeeting(host);
        return ResponseEntity.ok(meeting);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinMeeting(@RequestBody Map<String, String> request) {
        String meetingId = request.get("meetingId");
        String username = request.get("username");
        
        Meeting meeting = meetingService.getMeeting(meetingId);
        if (meeting == null) {
            return ResponseEntity.notFound().build();
        }
        
        meetingService.joinMeeting(meetingId, username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<Meeting> getMeeting(@PathVariable String meetingId) {
        Meeting meeting = meetingService.getMeeting(meetingId);
        if (meeting == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(meeting);
    }

    @PostMapping("/{meetingId}/leave")
    public ResponseEntity<?> leaveMeeting(@PathVariable String meetingId, @RequestParam String username) {
        Meeting meeting = meetingService.getMeeting(meetingId);
        if (meeting == null) return ResponseEntity.notFound().build();
        
        meetingService.leaveMeeting(meetingId, username);
        return ResponseEntity.ok().build();
    }
}