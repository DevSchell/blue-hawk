package com.example.blue_hawk.application.dto.matchParticipant;

public record UpdateMatchParticipantCommand(
        String id,
        String matchId,
        String userId
) {
}
