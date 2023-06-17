package com.codeninjas.spotaspot.auth.controller;

import com.codeninjas.spotaspot.auth.controller.dto.AuthenticationRequest;
import com.codeninjas.spotaspot.auth.controller.dto.RegisterRequest;
import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AuthenticationControllerIntegrationTest {

    private static final String AUTHENTICATE_URI = "/api/v1/auth/authenticate";
    private static final String REGISTER_URI = "/api/v1/auth/register";
    // For testing
    @Autowired
    private WebTestClient webTestClient;
    private User exampleUser;
    private User existingUser;

    @BeforeEach
    void setUp() throws Exception {
        LocalDateTime exampleTime = LocalDateTime.of(1970, 1, 1, 0, 0);
        exampleUser = User
                .builder()
                .email("mail@email.com")
                .username("username")
                .firstName("first")
                .lastName("last")
                .password("123456")
                .createdAt(exampleTime)
                .lastLogin(exampleTime)
                .lastChange(exampleTime)
                .role(Role.USER)
                .build();

        existingUser = User
                .builder()
                .email("email@gmail.com")
                .username("berko123")
                .firstName("bero")
                .lastName("beric")
                .password("123456")
                .createdAt(exampleTime)
                .lastLogin(exampleTime)
                .lastChange(exampleTime)
                .role(Role.USER)
                .build();

        webTestClient.post()
                .uri("/api/v1/auth/register")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(existingUser), RegisterRequest.class)
                .exchange();
    }

    @AfterEach
    void tearDown() {}

    @Test
    void register_should_succeed() {

        webTestClient.post()
                .uri(REGISTER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(exampleUser), RegisterRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("token").isNotEmpty();
    }

    @Test
    void register_should_throw_forbidden_when_existinguser_is_registered() {

        webTestClient.post()
                .uri(REGISTER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(existingUser), RegisterRequest.class)
                .exchange()
                .expectStatus()
                .isForbidden();
    }

    @Test
    void authenticate_should_succeed() {
        webTestClient.post()
                .uri(AUTHENTICATE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(
                        new AuthenticationRequest(
                                existingUser.getUsername(),
                                existingUser.getPassword())),
                        AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("token").isNotEmpty();
    }

    @Test
    void authenticate_should_fail_when_wrong_credentials() {
        webTestClient.post()
                .uri(AUTHENTICATE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(
                                new AuthenticationRequest(
                                        exampleUser.getUsername(),
                                        exampleUser.getPassword())),
                        AuthenticationRequest.class)
                .exchange()
                .expectStatus()
                .isForbidden();
    }
}