package com.example.blue_hawk.application.dto.matchParticipant;

public record ListMatchParticipantQuery(
        String matchId,
        String userId,
        Integer page,
        Integer size) {}
