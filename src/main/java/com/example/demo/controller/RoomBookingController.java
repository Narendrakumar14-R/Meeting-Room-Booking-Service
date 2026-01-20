package com.example.demo.controller;

import com.example.demo.entity.Room;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomBookingController {
    private final BookingService bookingService;
    @Autowired
    public RoomBookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @PostMapping("/{roomId}")
    public ResponseEntity<String> createBooking(@PathVariable String roomId,
                                                @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                @RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end){

        System.out.println("createBooking");
        boolean isBooked = bookingService
                .bookRoom(roomId, start, end);
        return  ResponseEntity.status(isBooked ? 201: 500)
               .body(isBooked? "Success": "failed");
    }
}

/*
* curl --location --request POST 'http://localhost:8080/api/v1/rooms/room1?start=2026-01-23T10%3A00%3A00&end=2026-01-24T11%3A00%3A00'
* */
