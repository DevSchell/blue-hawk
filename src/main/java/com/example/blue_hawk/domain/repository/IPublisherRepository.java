package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.Publisher;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IPublisherRepository {

    Publisher save(Publisher publisher);

    Optional<Publisher> findById(UUID publisherId);

    Page<Publisher> findAll(String name, Integer country);

    void deleteById(UUID publisherId);

    boolean existsById(UUID publisherId);
}
