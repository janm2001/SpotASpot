package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class InvalidAddEventException extends ApiException {

    public InvalidAddEventException() {
        super(HttpStatus.BAD_REQUEST, "cannot add event");
    }

}
