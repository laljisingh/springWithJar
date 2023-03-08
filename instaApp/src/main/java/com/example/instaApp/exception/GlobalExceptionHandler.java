package com.example.instaApp.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String MethodArgumentNotValidException(MethodArgumentNotValidException ex){
        String message = ex.getMessage();
        System.out.println(message);
        return message;
    }
}
