package com.example.blue_hawk.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticateUserCommand(
        @NotBlank String email,
        @NotBlank @Size(max = 72) String password
) {
}
