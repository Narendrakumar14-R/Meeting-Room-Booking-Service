package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;


public class Room {
    private final String id;
    private final List<Bookings> bookings  = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Room(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public List<Bookings> getBookings(){
        return bookings;
    }


}
