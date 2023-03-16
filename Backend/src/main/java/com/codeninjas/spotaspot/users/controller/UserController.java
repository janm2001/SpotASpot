package com.codeninjas.spotaspot.users.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @GetMapping("/get")
    public ResponseEntity<Object> get() {
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
