package com.example.blue_hawk.application.dto.offer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record GetOfferOutput(
        String id,
        String userBoardgameId,
        BigDecimal price,
        String status,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
