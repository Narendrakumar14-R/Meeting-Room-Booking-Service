package com.example.demo.service;

import com.example.demo.entity.Bookings;
import com.example.demo.entity.Room;
import com.example.demo.exception.NoRoomFoundExcpetion;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class BookingService {
    private final Map<String, Room>roomMap = new ConcurrentHashMap<>();


    public void createRoom(String id){
     roomMap.put(id, new Room(id));
    }

    public BookingService(){
        roomMap.put("room1", new Room("room1"));
    }

    @PostConstruct
    public void init(){
        roomMap.put("room1", new Room("room1"));
    }
    //Wadewang75024@gmail.com
    public boolean bookRoom(String id, LocalDateTime startTime, LocalDateTime endTime){
        if(Objects.isNull(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is null");
        }
        if(startTime.isAfter(endTime)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start time is after end time");
        }
        Room room = roomMap.get(id);

        if(Objects.isNull(room)){
           throw new NoRoomFoundExcpetion(String.format("No  room found with id %s", id));
        }

        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try{
            boolean isOverLappingTime  = room.getBookings().stream()
                    .anyMatch(room1 -> (startTime.isBefore(room1.getEndTime()) && endTime.isAfter(room1.getStartTime())));
            if(isOverLappingTime){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Room is already overlapping time");
            }
            room.getBookings().add(new Bookings(startTime, endTime));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"", e);
        }finally {
            lock.unlock();
        }
        return true;
    }

}
