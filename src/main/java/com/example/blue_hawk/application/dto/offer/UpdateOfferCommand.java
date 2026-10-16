package com.example.blue_hawk.application.dto.offer;

import java.math.BigDecimal;

public record UpdateOfferCommand(
        String id,
        String userBoardgameId,
        BigDecimal price,
        String status,
        String description
) {}
