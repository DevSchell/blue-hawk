package com.example.blue_hawk.application.dto.publisher;

public record CreatePublisherCommand(
        String id,
        String name,
        int country
) {
}
