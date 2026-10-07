package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.GetPublisherOutput;
import com.example.blue_hawk.application.dto.publisher.GetPublisherQuery;
import com.example.blue_hawk.domain.entity.publisher.Publisher;
import com.example.blue_hawk.domain.repository.IPublisherRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetPublisherUseCaseImpl implements GetPublisherUseCase {
    private final IPublisherRepository publisherRepository;

    public GetPublisherUseCaseImpl(IPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public GetPublisherOutput handle(GetPublisherQuery query) {
        Publisher publisher = publisherRepository.findById(UUID.fromString(query.id()))
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        return new GetPublisherOutput(
                publisher.getId().toString(),
                publisher.getName(),
                publisher.getCountry()
        );
    }
}
