package com.example.blue_hawk.infrastructure.persistence.matchParticipant;

import com.example.blue_hawk.domain.entity.MatchParticipant;
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

    public MatchParticipantRepositoryImpl(MatchParticipantJpaRepository jpaRepository) { this.jpaRepository = jpaRepository;}

    @Override
    public MatchParticipant save(MatchParticipant matchParticipant) {
        MatchParticipantJpaEntity jpaEntity = toJpaEntity(matchParticipant);
        MatchParticipantJpaEntity saved = jpaRepository.save(jpaEntity);
        return toDomain(saved);
    }

    @Override
    public Optional<MatchParticipant> findById(UUID matchParticipantId) {
        return jpaRepository.findById(matchParticipantId)
                .map(this::toDomain);
    }

    @Override
    public Page<MatchParticipant> findAll(UUID userId, UUID matchId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return jpaRepository.findAllFiltered(matchId, userId, pageable)
                .map(this::toDomain);
    }

    @Override
    public void deleteById(String matchParticipantId) { jpaRepository.deleteById(UUID.fromString(matchParticipantId));}

    @Override
    public boolean existsById(MatchParticipant matchParticipant) {
        if (matchParticipant == null || matchParticipant.getId() == null) {
            return false;
        }
        return jpaRepository.existsById(matchParticipant.getId());
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
                entity.getUserId(),
                entity.getMatchId()
        );
    }

}
