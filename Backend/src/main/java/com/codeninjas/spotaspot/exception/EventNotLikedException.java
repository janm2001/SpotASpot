package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class EventNotLikedException extends ApiException {
    public EventNotLikedException(Long eventId) {
        super(HttpStatus.BAD_REQUEST, "Event %d not liked.".formatted(eventId));
    }
}
