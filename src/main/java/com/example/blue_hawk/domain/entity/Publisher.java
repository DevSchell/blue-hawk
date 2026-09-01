package com.example.blue_hawk.domain.entity;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
///  Represents the responsible entity for the [BoardGame]
public class Publisher {
    private final UUID uuid;
    private final String name;
    private final String country;
    private final LocalDate releaseDate;

    ///  Constructor used in order to create a new [Publisher] without uuid
    public Publisher(String name, String country, LocalDate releaseDate) {
        this(UUID.randomUUID(), name, country, releaseDate);
    }

    ///  Constructor used in order to create a new [Publisher]  that came from the database
    public Publisher(UUID uuid, String name, String country, LocalDate releaseDate) {
        this.uuid = uuid;
        this.name = name;
        this.country = country;
        this.releaseDate = releaseDate;
    }
}
