package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.GetMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.GetMatchParticipantQuery;
import com.example.blue_hawk.domain.entity.matchparticipant.MatchParticipant;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetMatchParticipantUseCaseImpl implements GetMatchParticipantUseCase {

    private final IMatchParticipantRepository matchParticipantRepository;

    public GetMatchParticipantUseCaseImpl(IMatchParticipantRepository matchParticipantRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
    }

    @Override
    public GetMatchParticipantOutput handle(GetMatchParticipantQuery query) {
        MatchParticipant matchParticipant = matchParticipantRepository
                .findById(UUID.fromString(query.id()))
                .orElseThrow(() -> new RuntimeException("MatchParticipant not found with id: " + query.id()));

        return new GetMatchParticipantOutput(
                matchParticipant.getId().toString(),
                matchParticipant.getMatchId().toString(),
                matchParticipant.getUserId().toString()
        );
    }
}
