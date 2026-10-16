package com.example.blue_hawk.application.dto.user;

public record AuthenticateUserOutput(
        String accessToken,
        String tokenType,
        long expiresIn
) {
}
