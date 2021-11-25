package com.covelopfit.autotrading.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseResponse {
    private HttpStatus code;
    private String message;
    private Object data;

    public BaseResponse(HttpStatus code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}
