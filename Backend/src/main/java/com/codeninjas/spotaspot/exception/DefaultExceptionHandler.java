package com.codeninjas.spotaspot.exception;

import com.codeninjas.spotaspot.events.controller.EventController;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;
import java.time.Clock;
import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class DefaultExceptionHandler {

    Logger logger = LoggerFactory.getLogger(EventController.class);
    private final Clock clock;

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(EventNotFoundException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                e.getStatus().value(),
                e.getStatus().getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(UserNotOwnerException.class)
    public ResponseEntity<ApiErrorResponse> handleException(UserNotOwnerException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                e.getStatus().value(),
                e.getStatus().getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(InvalidDeleteEventException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidDeleteEventException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                e.getStatus().value(),
                e.getStatus().getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(InvalidAddEventException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InvalidAddEventException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                e.getStatus().value(),
                e.getStatus().getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(WrongUsernameOrPasswordException.class)
    public ResponseEntity<ApiErrorResponse> handleException(WrongUsernameOrPasswordException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                e.getStatus().value(),
                e.getStatus().getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(CouldNotRegisterException.class)
    public ResponseEntity<ApiErrorResponse> handleException(CouldNotRegisterException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                e.getStatus().value(),
                e.getStatus().getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(io.jsonwebtoken.security.SignatureException.class)
    public ResponseEntity<ApiErrorResponse> handleException(io.jsonwebtoken.security.SignatureException e,
                                                            HttpServletRequest request) {
        logger.info(request.getRemoteAddr() + " triggered an exception: " + e.getMessage());

        ApiErrorResponse response = new ApiErrorResponse(
                LocalDateTime.now(clock),
                401,
                "Unauthorized",
                e.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
