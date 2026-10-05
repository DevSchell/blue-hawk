package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.Match;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IMatchRepository {
    Match save(Match match);

    Optional<Match> findById(UUID matchId);

    Page<Match> findAll(UUID boardgameId, int page, int size);

    void deleteById(String matchId);

    boolean existsById(Match match);
}
