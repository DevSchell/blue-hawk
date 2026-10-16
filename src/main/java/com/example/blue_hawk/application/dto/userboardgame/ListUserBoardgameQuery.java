package com.example.blue_hawk.application.dto.userboardgame;

public record ListUserBoardgameQuery(
        String userId,
        String boardgameId,
        Integer page,
        Integer size) {
}
