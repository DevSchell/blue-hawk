package com.example.blue_hawk.application.dto.match;

public record ListMatchQuery(
        String boardgameId,
        String userId,
        int page,
        int size
) {}
