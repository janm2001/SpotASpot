package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class CouldNotRegisterException extends ApiException {

    public CouldNotRegisterException(String msg) {
        super(HttpStatus.FORBIDDEN, msg);
    }
}
