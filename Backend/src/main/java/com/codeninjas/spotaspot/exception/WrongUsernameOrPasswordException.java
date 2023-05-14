package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class WrongUsernameOrPasswordException extends ApiException {

    public WrongUsernameOrPasswordException() {
        super(HttpStatus.FORBIDDEN, "Wrong username or password");
    }
}
