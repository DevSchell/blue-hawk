package com.example.blue_hawk.infrastructure.persistence.matchParticipant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchParticipantJpaRepository extends JpaRepository<MatchParticipantJpaEntity, UUID> {

    @Query("SELECT mp FROM MatchParticipantJpaEntity mp WHERE " +
           "(:matchId IS NULL OR mp.matchId = :matchId) AND " +
           "(:userId IS NULL OR mp.userId = :userId)")
    Page<MatchParticipantJpaEntity> findAllFiltered(
            @Param("matchId") UUID matchId,
            @Param("userId") UUID userId,
            Pageable pageable);
}
