package com.codeninjas.spotaspot.events.service.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class EventNotFoundException extends Exception {

    public EventNotFoundException(Long id) {
        super("Event " + id + " not found");
    }
    public EventNotFoundException(Long id, Throwable cause) {
        super("Event " + id + " not found", cause);
    }

}
