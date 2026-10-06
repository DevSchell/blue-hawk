package com.example.blue_hawk.infrastructure.persistence.offer;

import com.example.blue_hawk.domain.entity.offer.Offer;
import com.example.blue_hawk.domain.entity.offer.OfferStatus;
import com.example.blue_hawk.domain.repository.IOfferRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class OfferRepositoryImpl implements IOfferRepository {

    private final OfferJpaRepository jpaRepository;

    public OfferRepositoryImpl(OfferJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Offer save(Offer offer) {
        OfferJpaEntity jpaEntity = toJpaEntity(offer);
        OfferJpaEntity saved = jpaRepository.save(jpaEntity);
        return toDomain(saved);
    }

    @Override
    public Optional<Offer> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Page<Offer> findAll(UUID userId, UUID boardgameId, OfferStatus status, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaRepository.findAllFiltered(userId, boardgameId, status, pageable)
                .map(this::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }

    private OfferJpaEntity toJpaEntity(Offer domain) {
        return new OfferJpaEntity(
                domain.getId(),
                domain.getUserBoardgameId(),
                domain.getPrice(),
                domain.getStatus(),
                domain.getDescription(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }

    private Offer toDomain(OfferJpaEntity entity) {
        return new Offer(
                entity.getId(),
                entity.getUserBoardgameId(),
                entity.getPrice(),
                entity.getStatus(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
