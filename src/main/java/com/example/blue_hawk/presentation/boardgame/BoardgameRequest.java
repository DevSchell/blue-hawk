package com.example.blue_hawk.presentation.boardgame;

public record BoardgameRequest(
        String name,
        String description,
        Integer releaseYear,
        String playerNumber,
        Integer playTime
) {
}
