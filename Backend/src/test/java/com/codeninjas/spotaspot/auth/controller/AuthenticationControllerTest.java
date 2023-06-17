package com.codeninjas.spotaspot.auth.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.auth.service.AuthenticationService;
import com.codeninjas.spotaspot.exception.WrongUsernameOrPasswordException;
import com.codeninjas.spotaspot.users.controller.dto.UserDTO;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
    private AutoCloseable autoCloseable;

    private AuthenticationController underTest;

    @Mock
    private AuthenticationService authenticationService;


    // Example data
    private User exampleUser;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AuthenticationController(authenticationService);

        LocalDateTime exampleTime = LocalDateTime.of(1970, 1, 1, 0, 0);
        exampleUser = new User(
                UUID.randomUUID(),
                "First",
                "Last",
                "email@email.com",
                "username",
                "123456",
                Role.USER,
                exampleTime,
                exampleTime,
                exampleTime
        );
    }

    @AfterEach
    void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    void register_should_return_token() throws Exception {
        // Given
        AuthenticationResponse expected = new AuthenticationResponse("123456", new UserDTO(exampleUser));
        doReturn(expected).when(authenticationService).register(any());
        RegisterRequest request = new RegisterRequest(exampleUser);

        // When
        ResponseEntity<?> response = underTest.register(request);

        // Then
        assertThat(response).isEqualTo(ResponseEntity.ok(expected));
    }

    @Test
    void authenticate_should_return_token() throws WrongUsernameOrPasswordException {
        // Given
        AuthenticationResponse expected = new AuthenticationResponse("123456", new UserDTO(exampleUser));
        doReturn(expected).when(authenticationService).authenticate(any());
        AuthenticationRequest request = new AuthenticationRequest(exampleUser.getUsername(), exampleUser.getPassword());

        // When
        ResponseEntity<?> response = underTest.authenticate(request);

        // Then
        assertThat(response).isEqualTo(ResponseEntity.ok(expected));
    }
}
