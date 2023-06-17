package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class EventAlreadyInterestedToException extends ApiException {
    public EventAlreadyInterestedToException(Long eventId) {
        super(HttpStatus.BAD_REQUEST, "Event %d already interested in".formatted(eventId));
    }
}
