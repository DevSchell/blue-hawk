package com.example.blue_hawk.domain.entity;

import java.util.UUID;

public class MatchParticipant {

    private UUID id;
    private UUID matchId;
    private UUID userId;

    public MatchParticipant(String matchId, String userId) {
        this.id = UUID.randomUUID();
        this.matchId = UUID.fromString(matchId);
        this.userId = UUID.fromString(userId);
    }

    public MatchParticipant(UUID id, UUID matchId, UUID userId) {
        this.id = id;
        this.matchId = matchId;
        this.userId = userId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
