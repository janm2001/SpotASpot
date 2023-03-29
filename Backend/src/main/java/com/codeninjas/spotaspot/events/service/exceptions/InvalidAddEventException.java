package com.codeninjas.spotaspot.events.service.exceptions;

public class InvalidAddEventException extends Exception {

    private static final String msg = "Cannot add event";

    public InvalidAddEventException() {
        super(msg);
    }
    public InvalidAddEventException(Throwable cause) {
        super(msg, cause);
    }

}
