package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.UpdatePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.UpdatePublisherOutput;
import com.example.blue_hawk.domain.entity.publisher.Publisher;
import com.example.blue_hawk.domain.repository.IPublisherRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdatePublisherUseCaseImpl implements UpdatePublisherUseCase {
    private final IPublisherRepository publisherRepository;

    public UpdatePublisherUseCaseImpl(IPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public UpdatePublisherOutput handle(UpdatePublisherCommand command) {
        Publisher publisher = publisherRepository.findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        publisher.setName(command.name());
        publisher.setCountry(command.country());

        Publisher saved = publisherRepository.save(publisher);

        return new UpdatePublisherOutput(
                saved.getId().toString(),
                saved.getName(),
                saved.getCountry()
        );
    }
}
