package com.codeninjas.spotaspot.users.controller;

import com.codeninjas.spotaspot.exception.UserNotFoundException;
import com.codeninjas.spotaspot.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "[AUTHENTICATED] get current logged in user info",
            description = "Get user info (authentication required)",
            security = @SecurityRequirement(name = "token"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned user data",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\n" +
                                                            "    \"id\": 3,\n" +
                                                            "    \"firstName\": \"Leonardo\",\n" +
                                                            "    \"lastName\": \"Marinović\",\n" +
                                                            "    \"email\": \"orgnaizer@gmail.com\",\n" +
                                                            "    \"username\": \"LeoOrg\",\n" +
                                                            "    \"role\": \"ORGANIZER\",\n" +
                                                            "    \"createdAt\": \"2023-04-14T17:25:09.444715\",\n" +
                                                            "    \"lastLogin\": \"2023-04-26T14:26:58.007192\",\n" +
                                                            "    \"lastChange\": \"2023-04-14T17:25:09.444715\"\n" +
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
                                                    value = "{\n" +
                                                            "    \"timestamp\": \"2023-04-24T20:23:02.925+00:00\",\n" +
                                                            "    \"status\": 403,\n" +
                                                            "    \"error\": \"Forbidden\",\n" +
                                                            "    \"message\": \"Forbidden\",\n" +
                                                            "    \"path\": \"/api/v1/user/get\"\n" +
                                                            "}"
                                            )
                                    }
                            )
                    )
            }

    )
    @GetMapping("current-user")
    public ResponseEntity<?> get() throws UsernameNotFoundException {
        return ResponseEntity.ok(userService.getCurrentUserDetails());
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserId(@PathVariable UUID userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
    @GetMapping("all")
    public ResponseEntity<?> getAllUsers(
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

}
