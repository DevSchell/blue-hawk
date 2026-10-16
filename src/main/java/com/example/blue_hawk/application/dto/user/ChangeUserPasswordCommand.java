package com.example.blue_hawk.application.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangeUserPasswordCommand(
        String id,

        @NotBlank
        @Size(max = 72)
        String currentPassword,

        @NotBlank
        @Size(min = 8, max = 72)
        String newPassword
) {
}
