package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class EventAlreadyLikedException extends ApiException {
    public EventAlreadyLikedException(Long eventId) {
        super(HttpStatus.BAD_REQUEST, "Event %d already liked".formatted(eventId));
    }
}
