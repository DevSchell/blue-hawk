package com.example.blue_hawk.application.dto.match;

public record UpdateMatchOutput(
        String id,
        String userBoardgameId,
        Integer maxUsers
) {}
