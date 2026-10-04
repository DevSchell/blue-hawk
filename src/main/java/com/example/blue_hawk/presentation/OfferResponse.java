package com.example.blue_hawk.presentation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OfferResponse(
        String id,
        String userBoardgameId,
        BigDecimal price,
        String status,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
