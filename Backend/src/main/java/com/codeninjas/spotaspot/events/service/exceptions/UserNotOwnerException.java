package com.codeninjas.spotaspot.events.service.exceptions;

public class UserNotOwnerException extends Exception {

    private static final String msg = "User is not owner";

    public UserNotOwnerException() {
        super(msg);
    }
    public UserNotOwnerException(Throwable cause) {
        super(msg, cause);
    }
}
