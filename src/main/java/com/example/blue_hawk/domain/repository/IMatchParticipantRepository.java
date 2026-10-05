package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.MatchParticipant;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IMatchParticipantRepository {
    MatchParticipant save(MatchParticipant matchParticipant);

    Optional<MatchParticipant> findById(UUID matchParticipantId);

    Page<MatchParticipant> findAll(UUID userId, UUID matchId, int page, int size);

    void deleteById(String matchParticipant);

    boolean existsById(MatchParticipant matchParticipant);
}
