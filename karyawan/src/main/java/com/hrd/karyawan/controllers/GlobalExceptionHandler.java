package com.hrd.karyawan.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)  //401
    public String handleResponseStatusException(ResponseStatusException ex){
        return "error/unauthorized";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)  //500
    public String handleException(Exception ex){
        return "error/error";
    }
}
