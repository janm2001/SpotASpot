package com.codeninjas.spotaspot.users.service;

import com.codeninjas.spotaspot.users.controller.dto.UserResponse;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public static UserResponse getCurrentUserDetails() throws UsernameNotFoundException {
        return new UserResponse(getCurrentUser());
    }

    public static User getCurrentUser() throws UsernameNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new UsernameNotFoundException("Bad token");
        }
        return user;
    }

}
