package com.codeninjas.spotaspot.auth.controller.dto;

import com.codeninjas.spotaspot.users.controller.dto.UserDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;

@JsonSerialize
public record AuthenticationResponse(
        @NonNull String token,
        @NonNull UserDTO userData
) {}
