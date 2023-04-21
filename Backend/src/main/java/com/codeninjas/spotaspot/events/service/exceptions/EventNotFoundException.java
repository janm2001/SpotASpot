package com.codeninjas.spotaspot.events.service.exceptions;

public class EventNotFoundException extends Exception {

    public EventNotFoundException(Long id) {
        super("Event " + id + " not found");
    }
    public EventNotFoundException(Long id, Throwable cause) {
        super("Event " + id + " not found", cause);
    }

}
