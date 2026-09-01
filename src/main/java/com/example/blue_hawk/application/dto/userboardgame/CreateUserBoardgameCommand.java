package com.example.blue_hawk.application.dto.userboardgame;

public record CreateUserBoardgameCommand(
        String id,
        String userId,
        String boardgameId
) {
}
