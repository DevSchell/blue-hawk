package com.example.blue_hawk.application.dto.boardgame;

public record ListBoardgameQuery(
        String name,
        Integer page,
        Integer size
) {
}
