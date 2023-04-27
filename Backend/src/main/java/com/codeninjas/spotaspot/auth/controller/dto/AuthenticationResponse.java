package com.codeninjas.spotaspot.auth.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;

@JsonSerialize
public record AuthenticationResponse(
        @NonNull String token
) {}
