package com.example.blue_hawk.application.dto.match;

public record CreateMatchOutput(
        String id,
        String boardgameId,
        String userId
) {}
