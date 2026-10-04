package com.example.blue_hawk.presentation;

import java.math.BigDecimal;

public record OfferRequest(
        String userBoardgameId,
        BigDecimal price,
        String status,
        String description
) {}
