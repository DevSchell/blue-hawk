package com.example.blue_hawk.infrastructure.persistence.publisher;

import com.example.blue_hawk.domain.entity.Publisher;
import com.example.blue_hawk.domain.repository.IPublisherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PublisherRepositoryImpl implements IPublisherRepository {

    private final PublisherJpaRepository jpaRepository;

    public PublisherRepositoryImpl(PublisherJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Publisher save(Publisher publisher) {
        PublisherJpaEntity entity = new PublisherJpaEntity(
                publisher.getId(), publisher.getName(), publisher.getCountry());
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Publisher> findById(UUID publisherId) {
        return jpaRepository.findById(publisherId).map(this::toDomain);
    }

    @Override
    public Page<Publisher> findAll(String name, Integer country) {
        return jpaRepository.findAllFiltered(name, country, Pageable.unpaged()).map(this::toDomain);
    }

    @Override
    public void deleteById(UUID publisherId) {
        jpaRepository.deleteById(publisherId);
    }

    @Override
    public boolean existsById(UUID publisherId) {
        return jpaRepository.existsById(publisherId);
    }

    private Publisher toDomain(PublisherJpaEntity entity) {
        return new Publisher(entity.getId(), entity.getName(), entity.getCountry());
    }
}
