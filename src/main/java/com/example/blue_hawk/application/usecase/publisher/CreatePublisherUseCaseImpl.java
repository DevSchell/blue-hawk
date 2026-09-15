package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.CreatePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.CreatePublisherOutput;
import com.example.blue_hawk.domain.entity.Publisher;
import com.example.blue_hawk.domain.repository.IPublisherRepository;
import org.springframework.stereotype.Service;

@Service
public class CreatePublisherUseCaseImpl implements CreatePublisherUseCase {
    private final IPublisherRepository publisherRepository;

    public CreatePublisherUseCaseImpl(IPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public CreatePublisherOutput handle(CreatePublisherCommand command) {
        Publisher publisher = new Publisher(command.name(), command.country());

        Publisher savedPublisher = publisherRepository.save(publisher);

        return new CreatePublisherOutput(
                savedPublisher.getId().toString(),
                savedPublisher.getName(),
                savedPublisher.getCountry()
        );
    }
}
