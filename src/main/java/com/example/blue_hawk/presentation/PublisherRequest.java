package com.example.blue_hawk.presentation;

import com.example.blue_hawk.domain.entity.publisher.Country;
public record PublisherRequest(
        String name,
        Country country
) {
}
