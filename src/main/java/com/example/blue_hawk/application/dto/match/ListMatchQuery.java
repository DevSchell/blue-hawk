package com.example.blue_hawk.application.dto.match;

public record ListMatchQuery(
        String userBoardgameId,
        int page,
        int size
) {}
