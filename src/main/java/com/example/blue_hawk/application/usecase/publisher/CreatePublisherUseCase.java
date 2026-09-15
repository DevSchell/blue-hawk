package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.CreatePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.CreatePublisherOutput;

public interface CreatePublisherUseCase {
    CreatePublisherOutput handle(CreatePublisherCommand command);
}
