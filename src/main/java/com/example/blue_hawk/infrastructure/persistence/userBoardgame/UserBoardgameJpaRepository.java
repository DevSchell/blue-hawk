package com.example.blue_hawk.infrastructure.persistence.userBoardgame;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserBoardgameJpaRepository extends JpaRepository<UserBoardgameJpaEntity, UUID> {

    @Query("SELECT u FROM UserBoardgameJpaEntity u WHERE " +
           "(:userId IS NULL OR u.userId = :userId) AND " +
           "(:boardgameId IS NULL OR u.boardgameId = :boardgameId)")
    Page<UserBoardgameJpaEntity> findAllFiltered(@Param("userId") UUID userId,
                                                 @Param("boardgameId") UUID boardgameId,
                                                 Pageable pageable);
}
