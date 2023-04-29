package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class UserNotOwnerException extends ApiException {

    public UserNotOwnerException() {
        super(HttpStatus.BAD_REQUEST, "User is not owner");
    }

}
