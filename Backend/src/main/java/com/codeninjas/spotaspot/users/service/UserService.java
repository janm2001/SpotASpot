package com.codeninjas.spotaspot.users.service;

import com.codeninjas.spotaspot.auth.service.JwtService;
import com.codeninjas.spotaspot.events.controller.dto.EventDTO;
import com.codeninjas.spotaspot.exception.UserNotFoundException;
import com.codeninjas.spotaspot.users.controller.dto.UserDTO;
import com.codeninjas.spotaspot.users.controller.dto.UserResponse;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserResponse getCurrentUserDetails() throws UsernameNotFoundException {
        System.out.println(jwtService.getCurrentUser().getAuthorities());
        return new UserResponse(jwtService.getCurrentUser());
    }

    public UserDTO getUserById(UUID userId) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return new UserDTO(user);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDTO::new);
    }
}
