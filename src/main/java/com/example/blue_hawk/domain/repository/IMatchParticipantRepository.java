package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.MatchParticipant;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IMatchParticipantRepository {

    MatchParticipant save(MatchParticipant matchParticipant);

    Optional<MatchParticipant> findById(UUID id);

    Page<MatchParticipant> findAll(UUID matchId, UUID userId, int page, int size);

    void deleteById(UUID id);

    boolean existsById(UUID id);
}
