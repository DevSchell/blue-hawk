package com.example.blue_hawk.application.dto.offer;

import java.math.BigDecimal;

public record PatchOfferCommand(
        String id,
        BigDecimal price,
        String status,
        String description
) {}
