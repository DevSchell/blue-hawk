package com.example.blue_hawk.application.dto.matchParticipant;

public record CreateMatchParticipantCommand(
        String matchId,
        String userId
) {}
