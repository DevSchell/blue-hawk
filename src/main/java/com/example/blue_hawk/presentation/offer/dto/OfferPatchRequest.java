package com.example.blue_hawk.presentation.offer.dto;

import java.math.BigDecimal;

public record OfferPatchRequest(
        BigDecimal price,
        String status,
        String description
) {}
