package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.DeletePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.DeletePublisherOutput;

public interface DeletePublisherUseCase {
    DeletePublisherOutput handle(DeletePublisherCommand command);
}
