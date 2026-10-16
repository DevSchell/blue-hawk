package com.example.blue_hawk.domain.entity.publisher;

import java.util.UUID;

public class Publisher {
    private UUID id;
    private String name;
    private Country country;

    public Publisher() {}

    public Publisher(String name, Country country) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.country = country;
    }

    public Publisher(UUID id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
