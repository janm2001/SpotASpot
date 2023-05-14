package com.codeninjas.spotaspot.exception;

import org.springframework.http.HttpStatus;

public class WrongSignatureException extends ApiException {

    public WrongSignatureException() {
        super(HttpStatus.UNAUTHORIZED, "Wrong signature");
    }
}
