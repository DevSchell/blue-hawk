package com.example.blue_hawk.application.dto.publisher;

public record ListPublisherQuery(
        String name,
        Integer country,
        Integer page,
        Integer size
) {
}
