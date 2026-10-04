package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.Offer;
import com.example.blue_hawk.domain.entity.OfferStatus;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IOfferRepository {

    Offer save(Offer offer);

    Optional<Offer> findById(UUID id);

    Page<Offer> findAll(UUID userId, UUID boardgameId, OfferStatus status, int page, int size);

    void deleteById(UUID id);

    boolean existsById(UUID id);
}
