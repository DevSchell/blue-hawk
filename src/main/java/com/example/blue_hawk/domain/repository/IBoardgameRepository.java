package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.Boardgame;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IBoardgameRepository {
    Boardgame save(Boardgame boardgame);

    Optional<Boardgame> findById(UUID boardgameId);

    Page<Boardgame> findAll(String name, int page, int size);

    void deleteById(String boardgameId);
}
