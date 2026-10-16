package com.example.blue_hawk.application.dto.userboardgame;

public record UpdateUserBoardgameCommand(
        String id,
        String userId,
        String boardgameId) {
}
