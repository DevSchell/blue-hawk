package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.ListPublisherOutput;
import com.example.blue_hawk.application.dto.publisher.ListPublisherQuery;

import java.util.List;

public interface ListPublisherUseCase {
    List<ListPublisherOutput> handle(ListPublisherQuery query);
}
