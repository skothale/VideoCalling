package com.example.videocallingbackend.model;

import java.util.HashSet;
import java.util.Set;

public class Meeting {
    private String meetingId;
    private String host;
    private Set<String> participants = new HashSet<>();
    private long createdAt;

    public Meeting(String meetingId, String host) {
        this.meetingId = meetingId;
        this.host = host;
        this.participants.add(host);
        this.createdAt = System.currentTimeMillis();
    }

    public String getMeetingId() { return meetingId; }
    public String getHost() { return host; }
    public Set<String> getParticipants() { return participants; }
    public long getCreatedAt() { return createdAt; }

    public void addParticipant(String user) { 
        participants.add(user); 
    }
    
    public void removeParticipant(String user) { 
        participants.remove(user); 
    }
}