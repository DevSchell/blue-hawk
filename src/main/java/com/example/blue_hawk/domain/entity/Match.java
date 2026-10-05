package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class Match {
    private UUID id;
    private UUID boardgameId;

    public Match(String boardgameId) {
        this.boardgameId = UUID.fromString(boardgameId);
        this.id = generateUUID();
    }

    public Match(UUID id, UUID boardgameId) {
        this.id = id;
        this.boardgameId = boardgameId;
    }

    private UUID generateUUID() {return UUID.randomUUID();}

    public UUID getId() { return id; }

    public void setId(UUID id) { this.id = id;}

    public UUID getBoardgameId() {return boardgameId;}

    public void setBoardgameId(UUID _boardgameId) {
        this.boardgameId = _boardgameId;
    }



}
