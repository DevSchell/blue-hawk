package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class MatchParticipant {
    private UUID id;
    private UUID userId;
    private UUID matchId;

    public MatchParticipant(String userId, String matchId) {
        this.userId = UUID.fromString(userId);
        this.matchId = UUID.fromString(matchId);
        this.id = generateUUID();
    }

    public MatchParticipant(UUID id, UUID userId, UUID matchId) {
        this.id = id;
        this.userId = userId;
        this.matchId = matchId;
    }

    private UUID generateUUID() { return UUID.randomUUID(); }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID _userId) {
        this.userId = _userId;
    }

    public UUID getMatchId() { return matchId;}

    public void setMatchId(UUID matchId) { this.matchId = matchId;}
}
