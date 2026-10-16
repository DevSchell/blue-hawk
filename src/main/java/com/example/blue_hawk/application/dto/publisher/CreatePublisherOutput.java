package com.example.blue_hawk.application.dto.publisher;

import com.example.blue_hawk.domain.entity.publisher.Country;
public record CreatePublisherOutput(
        String id,
        String name,
        Country country
) {
}
