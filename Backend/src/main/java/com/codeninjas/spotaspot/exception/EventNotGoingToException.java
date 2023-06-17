package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class EventNotGoingToException extends ApiException {
    public EventNotGoingToException(Long eventId) {
        super(HttpStatus.BAD_REQUEST, "Event %d not going to.".formatted(eventId));
    }
}
