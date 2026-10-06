package com.example.blue_hawk.domain.entity;

import java.util.Locale;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    // Guarda SEMPRE o hash da senha, nunca a senha em texto puro.
    private String passwordHash;
    private UserRole role;

    public User(String name, String email, String passwordHash) {
        this.id = generateUUID();
        this.name = name;
        this.email = normalizeEmail(email);
        this.passwordHash = passwordHash;
        this.role = UserRole.USER;
    }

    public User(UUID id, String name, String email, String passwordHash, UserRole role) {
        this.id = id;
        this.name = name;
        this.email = normalizeEmail(email);
        this.passwordHash = passwordHash;
        this.role = role;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public static String normalizeEmail(String email) {
        return email == null ? null : email.trim().toLowerCase(Locale.ROOT);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = normalizeEmail(email);
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
