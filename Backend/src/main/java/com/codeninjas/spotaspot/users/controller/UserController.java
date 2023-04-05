package com.codeninjas.spotaspot.users.controller;

import com.codeninjas.spotaspot.users.controller.dto.UserResponse;
import com.codeninjas.spotaspot.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity get() {
        UserResponse response;
        try {
            response = userService.getCurrentUserDetails();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

}
