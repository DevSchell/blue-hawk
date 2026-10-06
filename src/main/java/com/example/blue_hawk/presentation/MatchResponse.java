package com.example.blue_hawk.presentation;

public record MatchResponse(
        String id,
        String userBoardgameId,
        Integer maxUsers
) {}
