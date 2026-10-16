package com.example.blue_hawk.application.dto.matchParticipant;

public record ListMatchParticipantQuery(
        String matchId,
        String userId,
        int page,
        int size
) {}
