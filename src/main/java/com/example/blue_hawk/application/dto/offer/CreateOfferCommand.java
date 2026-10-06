package com.example.blue_hawk.application.dto.offer;

import java.math.BigDecimal;

public record CreateOfferCommand(
        String userBoardgameId,
        BigDecimal price,
        String status,
        String description
) {}
