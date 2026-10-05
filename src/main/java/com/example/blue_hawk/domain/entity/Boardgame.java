package com.example.blue_hawk.domain.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Boardgame {
    private UUID id;
    private String name;
    private String description;
    private int releaseYear;
    private String playerNumber;
    private int playTime;
}
