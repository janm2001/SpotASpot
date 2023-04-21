package com.codeninjas.spotaspot.auth.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request) {
        AuthenticationResponse response;
        try {
            response = authenticationService.register(request);
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Can't create user");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@Validated @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response;
        try {
            response = authenticationService.authenticate(request);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

}
