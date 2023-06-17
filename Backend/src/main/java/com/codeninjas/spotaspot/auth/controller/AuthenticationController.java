package com.codeninjas.spotaspot.auth.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.auth.service.AuthenticationService;
import com.codeninjas.spotaspot.exception.CouldNotRegisterException;
import com.codeninjas.spotaspot.exception.WrongUsernameOrPasswordException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    @Operation(
            summary = "Register new user",
            description = "Register Service",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "register"),
                    @ApiResponse(responseCode = "403", ref = "registerFailed") })
    public ResponseEntity<?> register(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "form for registration",
                    content = @Content(schema = @Schema(implementation = RegisterRequest.class)))
            RegisterRequest request) throws CouldNotRegisterException {

        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("authenticate")
    @Operation(
            summary = "Authenticate existing user",
            description = "Authentication Service",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "authenticate"),
                    @ApiResponse(responseCode = "403", ref = "authenticateFailed") })
    public ResponseEntity<?> authenticate(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Credentials",
                    content = @Content(schema = @Schema(implementation = AuthenticationRequest.class)))
            AuthenticationRequest request) throws WrongUsernameOrPasswordException {

        AuthenticationResponse response = authenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

}
