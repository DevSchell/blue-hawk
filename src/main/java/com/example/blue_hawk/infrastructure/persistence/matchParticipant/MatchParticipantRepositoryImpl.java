package com.example.blue_hawk.infrastructure.persistence.matchParticipant;

import com.example.blue_hawk.domain.entity.matchparticipant.MatchParticipant;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MatchParticipantRepositoryImpl implements IMatchParticipantRepository {

    private final MatchParticipantJpaRepository jpaRepository;

    public MatchParticipantRepositoryImpl(MatchParticipantJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MatchParticipant save(MatchParticipant matchParticipant) {
        MatchParticipantJpaEntity jpaEntity = toJpaEntity(matchParticipant);
        MatchParticipantJpaEntity saved = jpaRepository.save(jpaEntity);
        return toDomain(saved);
    }

    @Override
    public Optional<MatchParticipant> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Page<MatchParticipant> findAll(UUID matchId, UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaRepository.findAllFiltered(matchId, userId, pageable)
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

    private MatchParticipantJpaEntity toJpaEntity(MatchParticipant domain) {
        return new MatchParticipantJpaEntity(
                domain.getId(),
                domain.getMatchId(),
                domain.getUserId()
        );
    }

    private MatchParticipant toDomain(MatchParticipantJpaEntity entity) {
        return new MatchParticipant(
                entity.getId(),
                entity.getMatchId(),
                entity.getUserId()
        );
    }
}
