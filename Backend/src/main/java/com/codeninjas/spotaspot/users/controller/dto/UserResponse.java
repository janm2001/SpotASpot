package com.codeninjas.spotaspot.users.controller.dto;

import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDateTime;

@JsonSerialize
public record UserResponse(
        @NonNull Long id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull String username,
        @NonNull Role role,
        @NonNull LocalDateTime createdAt,
        @NonNull LocalDateTime lastLogin,
        @NonNull LocalDateTime lastChange
) {

    public UserResponse(User user) {
        this (
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedAt(),
                user.getLastLogin(),
                user.getLastChange()
        );
    }
}
