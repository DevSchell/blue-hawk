package com.example.blue_hawk.application.dto.match;

public record UpdateMatchCommand(
        String id,
        String boardgameId,
        String userId,
        Integer maxUsers
) {}
