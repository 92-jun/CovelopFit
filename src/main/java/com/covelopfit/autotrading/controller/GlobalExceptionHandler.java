package com.covelopfit.autotrading.controller;

import lombok.extern.slf4j.Slf4j;
import org.codehaus.groovy.tools.ErrorReporter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public void handleNoSuchAlgorithmException(){
        log.error("No Such Algorithm Exception Error!!");
    }

    @ExceptionHandler(IOException.class)
    public void handleIOException(){
        log.error("IO Exception Error!!");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(){
        log.error("Illegal Argument Exception Error!!");
    }

    @ExceptionHandler(Exception.class)
    public void handleException(){
        log.error("Exception Error!!");
    }


}
