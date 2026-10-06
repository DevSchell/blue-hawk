package com.example.blue_hawk.presentation.match.dto;

public record MatchRequest(
        String userBoardgameId,
        Integer maxUsers
) {}
