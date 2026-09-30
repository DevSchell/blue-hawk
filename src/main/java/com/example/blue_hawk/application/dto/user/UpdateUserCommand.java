package com.example.blue_hawk.application.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Campos nulos não são alterados. Se informados, não podem ser vazios.
public record UpdateUserCommand(
        String id,
        @Pattern(regexp = "(?s).*\\S.*", message = "must not be blank") @Size(max = 150) String name,
        @Pattern(regexp = "(?s).*\\S.*", message = "must not be blank") @Email @Size(max = 150) String email
) {
}
