package com.codeninjas.spotaspot.users.controller;

import com.codeninjas.spotaspot.auth.service.JwtService;
import com.codeninjas.spotaspot.users.controller.dto.UserResponse;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.codeninjas.spotaspot.users.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class UserControllerIntegrationTest {

    private final static LocalDateTime fixedTime = LocalDateTime.of(1989, 01, 13, 0, 0, 0, 0);
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void getShouldReturnUserData() throws Exception {
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
                .createdAt(fixedTime)
                .lastLogin(fixedTime)
                .lastChange(fixedTime)
                .role(Role.USER)
                .build();

        userRepository.save(user);

        String token = jwtService.generateToken(new HashMap<>(), user);

        UserResponse expected = new UserResponse(user);
        // when
        // then
        mockMvc.perform(get("/api/v1/user/get")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token))
                        .andExpect(status().isOk())
                        .andExpect(content().json(objectMapper.writeValueAsString(expected)));
    }
}