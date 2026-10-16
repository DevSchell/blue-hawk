package com.example.blue_hawk.application.dto.publisher;

import com.example.blue_hawk.domain.entity.publisher.Country;
public record ListPublisherQuery(
        String name,
        Country country,
        Integer page,
        Integer size
) {
}
