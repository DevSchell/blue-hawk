package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.DeletePublisherCommand;
import com.example.blue_hawk.application.dto.publisher.DeletePublisherOutput;
import com.example.blue_hawk.domain.repository.IPublisherRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeletePublisherUseCaseImpl implements DeletePublisherUseCase {
    private final IPublisherRepository publisherRepository;

    public DeletePublisherUseCaseImpl(IPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public DeletePublisherOutput handle(DeletePublisherCommand command) {
        publisherRepository.deleteById(UUID.fromString(command.id()));

        return new DeletePublisherOutput(command.id());
    }
}
