package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class Publisher {
    private UUID id;
    private String name;
    private int country;

    public Publisher(String name, int country) {
        this.name = name;
        this.country = country;
        this.id = generateUUID();
    }

    public Publisher(UUID id, String name, int country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }
}
