package com.example.demo.entity;

import java.time.LocalDateTime;

public class Bookings{

private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Bookings(LocalDateTime startTime, LocalDateTime endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
}
