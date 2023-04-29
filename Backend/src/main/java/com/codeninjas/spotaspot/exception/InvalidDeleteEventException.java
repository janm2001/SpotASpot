package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class InvalidDeleteEventException extends ApiException {

    public InvalidDeleteEventException(Long eventId) {
        super(HttpStatus.NOT_FOUND, "Cannot delete event: event " + eventId + " not found");
    }
}
