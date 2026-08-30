package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class UserBoardgame {
    private UUID id;
    private UUID userId;
    private UUID boardgameId;

    public UserBoardgame(UUID userId, UUID boardgameId) {
        this.userId = this.userId;
        this.boardgameId = this.boardgameId;
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
