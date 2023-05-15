package com.codeninjas.spotaspot.users.controller.dto;

import com.codeninjas.spotaspot.users.entity.Role;
import com.codeninjas.spotaspot.users.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonSerialize
public record UserDTO(
        @NonNull UUID id,
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        @NonNull String username,
        @NonNull Role role,
        @NonNull LocalDateTime createdAt,
        @NonNull LocalDateTime lastLogin,
        @NonNull LocalDateTime lastChange

) {
    public UserDTO(User user) {
        this(
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
