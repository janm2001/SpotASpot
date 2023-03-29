package com.codeninjas.spotaspot.auth.service;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationResponse;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.config.JwtService;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationServiceTest {

    private final static LocalDate LOCAL_DATE = LocalDate.of(1989, 01, 13);
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private Clock clock;

    private AuthenticationService authenticationService;
    private AutoCloseable autoCloseable;
    private LocalDateTime timeExample = LocalDateTime.of(2000, 10, 10, 10, 10);
    private Clock fixedClock;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        authenticationService = new AuthenticationService(
                userRepository,
                passwordEncoder,
                jwtService,
                authenticationManager,
                clock);

        doReturn("1234").when(jwtService).generateToken(any());

        fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void registerShouldReturnToken() throws Exception {
        // given
        User user = User.builder()
                .firstName("Leonardo")
                .lastName("Markovic")
                .email("Leonardo@gmail.com")
                .username("Leca")
                .password(passwordEncoder.encode("leonardo123"))
                .role(Role.USER)
                .createdAt(LocalDateTime.now(clock))
                .lastLogin(LocalDateTime.now(clock))
                .build();

        RegisterRequest regRequest = new RegisterRequest(user);

        // when
        AuthenticationResponse response = authenticationService.register(regRequest);

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
        assertThat(response)
                .isEqualTo(AuthenticationResponse
                    .builder()
                    .token("1234")
                    .build());
    }

    @Test
    void authenticateShouldReturnToken() throws Exception {
        // given
        User user = User.builder()
                .firstName("Leonardo")
                .lastName("Markovic")
                .email("Leonardo@gmail.com")
                .username("leca12")
                .password(passwordEncoder.encode("leonardo123"))
                .role(Role.USER)
                .createdAt(timeExample)
                .lastLogin(timeExample)
                .build();

        AuthenticationRequest request = AuthenticationRequest
                .builder()
                .username("leca12")
                .password("leonardo123")
                .build();

        doReturn(Optional.of(user)).when(userRepository).findByUsername("leca12");

        // when
        AuthenticationResponse response = authenticationService.authenticate(request);
        // todo: needs fix
        user.setLastLogin(LocalDateTime.now(clock));

        // then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        // This check doesn't work
        assertThat(capturedUser).isEqualTo(user);
        assertThat(response)
                .isEqualTo(AuthenticationResponse
                        .builder()
                        .token("1234")
                        .build());
    }

    @Test
    void authenticateWillThrowWhenWrongUsername() {
        // given
        AuthenticationRequest request = AuthenticationRequest
                .builder()
                .username("leca12")
                .password("leonardo123")
                .build();

        doReturn(Optional.empty()).when(userRepository).findByUsername(any());

        // when
        Throwable thrown = catchThrowable(() -> {
            authenticationService.authenticate(request);
        });
        // then
        assertThat(thrown)
                .isInstanceOf(UsernameNotFoundException.class);
        verify(userRepository, never()).save(any());
    }
}