package com.example.blue_hawk.infrastructure.persistence.match;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchJpaEntity, UUID> {

    @Query("SELECT m FROM MatchJpaEntity m WHERE " +
           "(:boardgameId IS NULL OR m.boardgameId = :boardgameId) AND " +
           "(:userId IS NULL OR m.userId = :userId)")
    Page<MatchJpaEntity> findAllFiltered(
            @Param("boardgameId") UUID boardgameId,
            @Param("userId") UUID userId,
            Pageable pageable);
}
