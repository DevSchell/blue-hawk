package com.example.blue_hawk.application.usecase.publisher;

import com.example.blue_hawk.application.dto.publisher.ListPublisherOutput;
import com.example.blue_hawk.application.dto.publisher.ListPublisherQuery;
import com.example.blue_hawk.domain.repository.IPublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPublisherUseCaseImpl implements ListPublisherUseCase {
    private final IPublisherRepository publisherRepository;

    public ListPublisherUseCaseImpl(IPublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<ListPublisherOutput> handle(ListPublisherQuery query) {
        return publisherRepository.findAll(query.name(), query.country())
                .stream()
                .map(publisher -> new ListPublisherOutput(
                        publisher.getId().toString(),
                        publisher.getName(),
                        publisher.getCountry()
                ))
                .toList();
    }
}
