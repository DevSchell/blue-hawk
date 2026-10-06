package com.example.blue_hawk.application.dto.user;

public record ListUserQuery(
        String name,
        String email,
        Integer page,
        Integer size
) {
}
