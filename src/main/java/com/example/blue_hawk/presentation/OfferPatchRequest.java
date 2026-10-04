package com.example.blue_hawk.presentation;

import java.math.BigDecimal;

public record OfferPatchRequest(
        BigDecimal price,
        String status,
        String description
) {}
