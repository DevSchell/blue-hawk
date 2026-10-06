package com.example.blue_hawk.presentation;

public record MatchRequest(
        String userBoardgameId,
        Integer maxUsers
) {}
