package com.example.blue_hawk.domain.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Boardgame {
    private UUID id;
    private String name;
    private String description;
    private int releaseYear;
    private String playerNumber;
    private int playTime;

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
}
