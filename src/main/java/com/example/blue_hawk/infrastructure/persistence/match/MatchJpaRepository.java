package com.example.blue_hawk.infrastructure.persistence.match;

import com.example.blue_hawk.infrastructure.persistence.userBoardgame.UserBoardgameJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MatchJpaRepository extends JpaRepository<MatchJpaEntity, UUID> {

    @Query("SELECT u FROM MatchJpaEntity u WHERE" +
            "(:boardgameId IS NULL OR u.boardgameId = :boardgameId)")
    Page<MatchJpaEntity> findAllFiltered(@Param("boardgameId") UUID boardgameId,
                                                 Pageable pageable);
}
