package com.example.blue_hawk.application.dto.offer;

public record ListOfferQuery(
        String userId,
        String boardgameId,
        String status,
        int page,
        int size
) {}
