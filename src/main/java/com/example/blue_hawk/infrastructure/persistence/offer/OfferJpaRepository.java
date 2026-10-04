package com.example.blue_hawk.infrastructure.persistence.offer;

import com.example.blue_hawk.domain.entity.OfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfferJpaRepository extends JpaRepository<OfferJpaEntity, UUID> {

    @Query("SELECT o FROM OfferJpaEntity o " +
           "JOIN UserBoardgameJpaEntity ubg ON o.userBoardgameId = ubg.id " +
           "WHERE (:userId IS NULL OR ubg.userId = :userId) " +
           "AND (:boardgameId IS NULL OR ubg.boardgameId = :boardgameId) " +
           "AND (:status IS NULL OR o.status = :status)")
    Page<OfferJpaEntity> findAllFiltered(
            @Param("userId") UUID userId,
            @Param("boardgameId") UUID boardgameId,
            @Param("status") OfferStatus status,
            Pageable pageable);
}
