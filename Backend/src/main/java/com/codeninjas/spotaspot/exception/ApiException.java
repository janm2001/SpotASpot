package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends Exception {
    private final HttpStatus status;
    public ApiException (HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
