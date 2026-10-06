package com.example.blue_hawk.presentation;

public record BoardgameRequest(
        String name,
        String description,
        Integer releaseYear,
        String playerNumber,
        Integer playTime
) {
}
