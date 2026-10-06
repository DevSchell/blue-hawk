package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class Match {

    private UUID id;
    private UUID userBoardgameId;
    private Integer maxUsers;

    public Match(String userBoardgameId, Integer maxUsers) {
        this.id = UUID.randomUUID();
        this.userBoardgameId = UUID.fromString(userBoardgameId);
        this.maxUsers = maxUsers;
    }

    public Match(UUID id, UUID userBoardgameId, Integer maxUsers) {
        this.id = id;
        this.userBoardgameId = userBoardgameId;
        this.maxUsers = maxUsers;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserBoardgameId() {
        return userBoardgameId;
    }

    public void setUserBoardgameId(UUID userBoardgameId) {
        this.userBoardgameId = userBoardgameId;
    }

    public Integer getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(Integer maxUsers) {
        this.maxUsers = maxUsers;
    }
}
