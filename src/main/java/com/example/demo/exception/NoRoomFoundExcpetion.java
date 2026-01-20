package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoRoomFoundExcpetion extends RuntimeException {
    public NoRoomFoundExcpetion(String message){
        super(message);
    }
}
