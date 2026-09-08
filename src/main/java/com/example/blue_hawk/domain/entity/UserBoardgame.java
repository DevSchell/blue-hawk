package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class UserBoardgame {
    private UUID id;
    private UUID userId;
    private UUID boardgameId;

    public UserBoardgame(String userId, String boardgameId) {
        this.userId = UUID.fromString(userId);
        this.boardgameId = UUID.fromString(boardgameId);
        this.id = generateUUID();
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID _userId) {
        this.userId = _userId;
    }

    public UUID getBoardgameId() {
        return boardgameId;
    }

    public void setBoardgameId(UUID _boardgameId) {
        this.boardgameId = _boardgameId;
    }
}
