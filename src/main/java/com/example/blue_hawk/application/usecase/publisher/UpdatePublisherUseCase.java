package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.UpdatePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.UpdatePublisherOutput;

public interface UpdatePublisherUseCase {
    UpdatePublisherOutput handle(UpdatePublisherCommand command);
}
