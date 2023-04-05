package com.codeninjas.spotaspot.events.service.exceptions;

public class InvalidDeleteEventException extends Exception {

    private static final String msg = "Cannot delete event: event not found";

    public InvalidDeleteEventException() {
        super(msg);
    }
    public InvalidDeleteEventException(Throwable cause) {
        super(msg, cause);
    }

}
