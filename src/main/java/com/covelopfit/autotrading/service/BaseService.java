package com.covelopfit.autotrading.service;

import com.covelopfit.autotrading.common.ResponseCode;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    protected ResponseCode responseCode;
    protected String errorMessage;

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
