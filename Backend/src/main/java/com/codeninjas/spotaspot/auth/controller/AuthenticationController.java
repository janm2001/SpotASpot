package com.codeninjas.spotaspot.auth.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.auth.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(
            summary = "Register new user",
            description = "Register Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully registered",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"token\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMZW9" +
                                                            "PcmciLCJpYXQiOjE2ODIxODI0NDYsImV4cCI6MTY4MjE4Mzg4Nn0." +
                                                            "nbJdWwR0L4Sr2QXskvLULwuUVu01tyVCiKOdfGPzxgQ\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "Can't create user"
                                            )
                                    }
                            )
                    )
            }

    )
    public ResponseEntity<?> register(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "    \"firstName\": \"Leonardo\",\n" +
                                                    "    \"lastName\": \"Marinović\",\n" +
                                                    "    \"email\": \"orgnaizer23@gmail.com\",\n" +
                                                    "    \"role\": \"ORGANIZER\",\n" +
                                                    "    \"username\": \"LeoOrg67\",\n" +
                                                    "    \"password\": \"123456\"\n" +
                                                    "}"
                                    )
                            }
                    )
            )
            RegisterRequest request) {
        AuthenticationResponse response;
        try {
            response = authenticationService.register(request);
        } catch (DataAccessException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Can't create user");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/authenticate")
    @Operation(
            summary = "Authenticate existing user",
            description = "Authentication Service",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully authenticated",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"token\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJMZW9" +
                                                            "PcmciLCJpYXQiOjE2ODIxODI0NDYsImV4cCI6MTY4MjE4Mzg4Nn0." +
                                                            "nbJdWwR0L4Sr2QXskvLULwuUVu01tyVCiKOdfGPzxgQ\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "Bad credentials"
                                            )
                                    }
                            )
                    )
            }

    )
    public ResponseEntity<?> authenticate(
            @Validated
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            value = "{\n" +
                                                    "    \"username\": \"LeoOrg\",\n" +
                                                    "    \"password\": \"1234567\"\n" +
                                                    "}"
                                    )
                            }
                    )
            )
            AuthenticationRequest request) {
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
