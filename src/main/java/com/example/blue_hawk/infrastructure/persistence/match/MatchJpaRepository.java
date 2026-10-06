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
           "(:userBoardgameId IS NULL OR m.userBoardgameId = :userBoardgameId)")
    Page<MatchJpaEntity> findAllFiltered(
            @Param("userBoardgameId") UUID userBoardgameId,
            Pageable pageable);
}
