package com.covelopfit.autotrading.common;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(200, "Success"),
    ACCEPTED(202, "Accepted"),
    INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
    BAD_REQUEST(400,"Bad Request");

    private final String message;
    private final int status;

    ResponseCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
