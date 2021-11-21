package com.covelopfit.autotrading.controller;

import com.covelopfit.autotrading.response.AutoTradingCommonResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {RuntimeException.class})
    protected AutoTradingCommonResponse exceptionHandler(Exception e) {
        log.error(String.format("[error] 확인되지 않은 에러 %s", e.toString()));
        return new AutoTradingCommonResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러", null);
    }

}
