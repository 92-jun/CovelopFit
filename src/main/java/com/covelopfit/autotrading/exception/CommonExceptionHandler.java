package com.covelopfit.autotrading.exception;

import com.covelopfit.autotrading.common.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.NoSuchAlgorithmException;


@ControllerAdvice
public class CommonExceptionHandler {

    protected ResponseCode responseCode;
    protected String errorMessage;

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity exceptionHandler(IllegalArgumentException e) {
        setResponseCode(ResponseCode.INTERNAL_SERVER_ERROR);
        setErrorMessage("특정 Excpetion, IllegalArgumentException : " + e.getMessage());
        return getErrorResponse();
    }


    private ResponseEntity getErrorResponse() {
        return new ResponseEntity<>(getErrorResponseJson(), HttpStatus.valueOf(getResponseCode().getStatus()));
    }

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    protected void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }

    protected void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorResponseJson() {
        StringBuilder errorResponseJson = new StringBuilder();
        String errorString = this.responseCode.getMessage() + " (" + this.errorMessage + ")";
        errorResponseJson.append("{\"error\":\"");
        errorResponseJson.append(errorString);
        errorResponseJson.append("\"}");
        return errorResponseJson.toString();
    }
}
