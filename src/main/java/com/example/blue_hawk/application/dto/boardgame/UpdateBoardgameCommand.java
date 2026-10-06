package com.example.blue_hawk.application.dto.boardgame;

public record UpdateBoardgameCommand(
        String id,
        String name,
        String description,
        Integer releaseYear,
        String playerNumber,
        Integer playTime
) {
}
