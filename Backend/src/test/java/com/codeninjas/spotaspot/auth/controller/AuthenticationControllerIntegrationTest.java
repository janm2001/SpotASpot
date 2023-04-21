package com.codeninjas.spotaspot.auth.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthenticationControllerIntegrationTest {

    private final static LocalDate LOCAL_DATE = LocalDate.of(1989, 01, 13);

    private final MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private final UserRepository userRepository;

    @Mock
    private Clock clock;
    private Clock fixedClock;

    private PasswordEncoder passwordEncoder;

    public AuthenticationControllerIntegrationTest(MockMvc mockMvc, UserRepository userRepository) {
        this.mockMvc = mockMvc;
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {

        objectMapper = new ObjectMapper();

        fixedClock = Clock.fixed(LOCAL_DATE.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();
        doReturn(fixedClock.millis()).when(clock).millis();
    }

    @AfterEach
    void tearDown() {
    }

    @WithAnonymousUser
    @Test
    void registerShouldReturnToken() throws Exception {
        // given
        String password = "1234";
        User user = User
                .builder()
                .email("Leon@gmail.com")
                .username("Leonardo1234")
                .firstName("Leonardo")
                .lastName("Markovic")
                .password(password)
                .createdAt(LocalDateTime.now(clock))
                .lastLogin(LocalDateTime.now(clock))
                .lastChange(LocalDateTime.now(clock))
                .role(Role.USER)
                .build();

        RegisterRequest request = new RegisterRequest(user);
        // when
        // then
        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

    }

    @WithAnonymousUser
    @Test
    void authenticateShouldReturnToken() throws Exception {
        // given
        String username = "Leonardo123";
        String password = "1234";

        User user = User
                .builder()
                .email("Leo@gmail.com")
                .username(username)
                .firstName("Leonardo")
                .lastName("Markovic")
                .password(passwordEncoder.encode(password))
                .createdAt(LocalDateTime.now(fixedClock))
                .lastLogin(LocalDateTime.now(fixedClock))
                .lastChange(LocalDateTime.now(fixedClock))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        AuthenticationRequest request = new AuthenticationRequest(username, password);

        // when
        // then
        mockMvc.perform(post("/api/v1/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}