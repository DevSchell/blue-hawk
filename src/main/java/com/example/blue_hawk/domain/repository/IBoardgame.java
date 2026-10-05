package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.Boardgame;

import java.util.Optional;
import java.util.UUID;

public interface IBoardgame {
    Boardgame save(Boardgame boardgame);

    Optional<Boardgame> findById(UUID boardgameId);

    boolean existsById(UUID boardgameId);
}
