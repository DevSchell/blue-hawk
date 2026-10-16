package com.example.blue_hawk.domain.entity.boardgame;

import java.util.UUID;

public class Boardgame {
    private UUID id;
    private String name;
    private String description;
    private int releaseYear;
    private String playerNumber;
    private int playTime;

    public Boardgame() {}

    public Boardgame(String name, String description, int releaseYear, String playerNumber, int playTime) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        this.playerNumber = playerNumber;
        this.playTime = playTime;
    }

    public Boardgame(UUID id, String name, String description, int releaseYear, String playerNumber, int playTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseYear = releaseYear;
        this.playerNumber = playerNumber;
        this.playTime = playTime;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }
}
