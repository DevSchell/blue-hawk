package com.example.blue_hawk.presentation.match.dto;

public record MatchResponse(
        String id,
        String userBoardgameId,
        Integer maxUsers
) {}
