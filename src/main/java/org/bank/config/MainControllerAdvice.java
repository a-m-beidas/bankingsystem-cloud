package com.example.testsecurity.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MainControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<String> genericHandler(Exception e) throws Exception {
        e.printStackTrace();
        //TODO add a logger
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<String> genericHandler(IllegalArgumentException e) throws Exception {
        e.printStackTrace();
        //TODO add a logger
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
