package com.example.blue_hawk.infrastructure.persistence.boardgame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BoardgameJpaRepository extends JpaRepository<BoardgameJpaEntity, UUID> {

}
