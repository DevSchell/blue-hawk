package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.UpdateMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.UpdateMatchParticipantOutput;
import com.example.blue_hawk.domain.entity.MatchParticipant;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateMatchParticipantUseCaseImpl implements UpdateMatchParticipantUseCase{

    private final IMatchParticipantRepository matchParticipantRepository;

    public UpdateMatchParticipantUseCaseImpl(IMatchParticipantRepository matchParticipantRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
    }

    @Override
    public UpdateMatchParticipantOutput handle(UpdateMatchParticipantCommand command) {
        MatchParticipant matchParticipant = matchParticipantRepository
                .findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("MatchParticipant not found"));

        matchParticipant.setUserId(UUID.fromString(command.userId()));
        matchParticipant.setMatchId(UUID.fromString(command.matchId()));

        MatchParticipant updated = matchParticipantRepository.save(matchParticipant);

        return new UpdateMatchParticipantOutput(
                updated.getId().toString(),
                updated.getMatchId().toString(),
                updated.getMatchId().toString());
    }
}
