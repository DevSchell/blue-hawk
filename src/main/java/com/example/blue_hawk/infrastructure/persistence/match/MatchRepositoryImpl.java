package com.example.blue_hawk.infrastructure.persistence.match;

import com.example.blue_hawk.domain.entity.Match;
import com.example.blue_hawk.domain.repository.IMatchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MatchRepositoryImpl implements IMatchRepository {

    private final MatchJpaRepository jpaRepository;

    public MatchRepositoryImpl(MatchJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Match save(Match match) {
        MatchJpaEntity jpaEntity = toJpaEntity(match);
        MatchJpaEntity saved = jpaRepository.save(jpaEntity);
        return toDomain(saved);

    }

    @Override
    public Optional<Match> findById(UUID matchId) {
        return jpaRepository.findById(matchId)
                .map(this::toDomain);
    }

    @Override
    public Page<Match> findAll( UUID boardgameId, int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return jpaRepository.findAllFiltered(boardgameId, pageable)
                .map(this::toDomain);
    }

    @Override
    public void deleteById(String matchId) {
        jpaRepository.deleteById(UUID.fromString(matchId));
    }

    @Override
    public boolean existsById(Match match) {
        if (match == null || match.getId() == null) {
            return false;
        }
        return jpaRepository.existsById(match.getId());
    }

    private MatchJpaEntity toJpaEntity(Match domain) {
        return new MatchJpaEntity(
                domain.getId(),
                domain.getBoardgameId()
        );
    }

    private Match toDomain(MatchJpaEntity entity) {
        return new Match(
                entity.getId(),
                entity.getBoardgameId()
        );
    }
}
