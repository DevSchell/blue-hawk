package com.example.blue_hawk.presentation.boardgame;

public record BoardgameResponse(
        String id,
        String name,
        String description,
        Integer releaseYear,
        String playerNumber,
        Integer playTime
) {
}
