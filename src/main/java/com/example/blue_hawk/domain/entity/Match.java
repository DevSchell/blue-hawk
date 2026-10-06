package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class Match {

    private UUID id;
    private UUID boardgameId;
    private UUID userId;

    public Match(String boardgameId, String userId) {
        this.id = UUID.randomUUID();
        this.boardgameId = UUID.fromString(boardgameId);
        this.userId = UUID.fromString(userId);
    }

    public Match(UUID id, UUID boardgameId, UUID userId) {
        this.id = id;
        this.boardgameId = boardgameId;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBoardgameId() {
        return boardgameId;
    }

    public void setBoardgameId(UUID boardgameId) {
        this.boardgameId = boardgameId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
