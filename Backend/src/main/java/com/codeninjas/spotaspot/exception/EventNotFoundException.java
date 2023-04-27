package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class EventNotFoundException extends ApiException {

    public EventNotFoundException(Long eventId) {
        super(HttpStatus.NOT_FOUND, "Event " + eventId + " not found");
    }
}
