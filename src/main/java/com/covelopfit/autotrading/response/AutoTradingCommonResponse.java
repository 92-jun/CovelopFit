package com.covelopfit.autotrading.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AutoTradingCommonResponse {

    private HttpStatus code;
    private String message;
    private Object data;

    public AutoTradingCommonResponse(HttpStatus code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
