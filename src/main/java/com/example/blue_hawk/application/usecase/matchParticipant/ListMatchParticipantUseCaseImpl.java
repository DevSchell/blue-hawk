package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantQuery;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListMatchParticipantUseCaseImpl implements ListMatchParticipantUseCase {

    private final IMatchParticipantRepository matchParticipantRepository;

    public ListMatchParticipantUseCaseImpl(IMatchParticipantRepository matchParticipantRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
    }

    @Override
    public List<ListMatchParticipantOutput> handle(ListMatchParticipantQuery query) {
        UUID matchId = query.matchId() != null ? UUID.fromString(query.matchId()) : null;
        UUID userId = query.userId() != null ? UUID.fromString(query.userId()) : null;

        return matchParticipantRepository
                .findAll(matchId, userId, query.page(), query.size())
                .getContent()
                .stream()
                .map(mp -> new ListMatchParticipantOutput(
                        mp.getId().toString(),
                        mp.getMatchId().toString(),
                        mp.getUserId().toString()))
                .toList();
    }
}
