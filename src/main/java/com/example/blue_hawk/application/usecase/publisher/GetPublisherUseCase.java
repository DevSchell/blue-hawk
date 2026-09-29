package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.GetPublisherOutput;
import com.example.blue_hawk.application.dto.publisher.GetPublisherQuery;

public interface GetPublisherUseCase {
    GetPublisherOutput handle(GetPublisherQuery query);
}
