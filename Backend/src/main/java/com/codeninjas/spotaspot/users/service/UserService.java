package com.codeninjas.spotaspot.users.service;

import com.codeninjas.spotaspot.auth.service.JwtService;
import com.codeninjas.spotaspot.users.controller.dto.UserResponse;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserResponse getCurrentUserDetails() throws UsernameNotFoundException {
        System.out.println(jwtService.getCurrentUser().getAuthorities());
        return new UserResponse(jwtService.getCurrentUser());
    }

}
