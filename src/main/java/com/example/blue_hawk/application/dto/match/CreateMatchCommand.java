package com.example.blue_hawk.application.dto.match;

public record CreateMatchCommand(
        String boardgameId,
        String userId,
        Integer maxUsers
) {}
