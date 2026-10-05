package com.example.blue_hawk.application.dto.match;

public record ListMatchQuery(
        String boardgameId,
        Integer page,
        Integer size
) {
}
