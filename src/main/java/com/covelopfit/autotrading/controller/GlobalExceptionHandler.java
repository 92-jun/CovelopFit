package com.covelopfit.autotrading.controller;


import com.covelopfit.autotrading.dto.BaseResponse;
import com.covelopfit.autotrading.dto.OrderApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected BaseResponse illegalArgumentExceptionHandler(Exception e) {
        log.error(String.format("[error] Argument 에러 %s", e.toString()));
        e.printStackTrace();
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "서비스 내 API argument 에러");
    }


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    protected BaseResponse exceptionHandler(Exception e) {
        log.error(String.format("[error] 확인되지 않은 에러 %s", e.toString()));
        e.printStackTrace();
        return new BaseResponse(HttpStatus.INTERNAL_SERVER_ERROR, "확인되지 않은 에러 발생");
    }

}
