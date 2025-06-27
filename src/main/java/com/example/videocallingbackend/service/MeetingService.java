package com.example.videocallingbackend.service;

import com.example.videocallingbackend.model.Meeting;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MeetingService {
    private final Map<String, Meeting> meetings = new ConcurrentHashMap<>();

    public Meeting createMeeting(String host) {
        String meetingId = generateMeetingId();
        Meeting meeting = new Meeting(meetingId, host);
        meetings.put(meetingId, meeting);
        return meeting;
    }

    public Meeting getMeeting(String meetingId) {
        return meetings.get(meetingId);
    }

    public void removeMeeting(String meetingId) {
        meetings.remove(meetingId);
    }

    public void joinMeeting(String meetingId, String username) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting != null) {
            meeting.addParticipant(username);
        }
    }

    public void leaveMeeting(String meetingId, String username) {
        Meeting meeting = meetings.get(meetingId);
        if (meeting != null) {
            meeting.removeParticipant(username);
            // Remove meeting if no participants left
            if (meeting.getParticipants().isEmpty()) {
                meetings.remove(meetingId);
            }
        }
    }

    private String generateMeetingId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            if (i > 0) sb.append("-");
            for (int j = 0; j < 3; j++) {
                sb.append(chars.charAt((int) (Math.random() * chars.length())));
            }
        }
        return sb.toString();
    }
} 