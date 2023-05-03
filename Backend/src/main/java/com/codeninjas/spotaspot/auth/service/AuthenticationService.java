package com.codeninjas.spotaspot.auth.service;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.exception.CouldNotRegisterException;
import com.codeninjas.spotaspot.exception.WrongUsernameOrPasswordException;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final Clock clock;

    public AuthenticationResponse register(RegisterRequest request) throws CouldNotRegisterException {
        User user = request.toUser(passwordEncoder, LocalDateTime.now(clock));
        try {
            userRepository.save(user);
        } catch(DataAccessException e) {
            throw new CouldNotRegisterException(e.getMessage());
        }

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws WrongUsernameOrPasswordException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.username(),
                    request.password()));
        } catch(AuthenticationException e) {
            throw new WrongUsernameOrPasswordException();
        }

        User user = userRepository.findByUsername(request.username()).orElseThrow();
        user.setLastLogin(LocalDateTime.now(clock));
        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

}
