package com.example.blue_hawk.application.dto.matchParticipant;

public record CreateMatchParticipantOutput(
        String id,
        String matchId,
        String userId
) {}
