package com.example.blue_hawk.application.dto.user;

import com.example.blue_hawk.domain.entity.user.User;

public record UserOutput(
        String id,
        String name,
        String email,
        String role
) {
    public static UserOutput from(User user) {
        return new UserOutput(user.getId().toString(), user.getName(), user.getEmail(), user.getRole().name());
    }
}
