package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.CreateMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.CreateMatchParticipantOutput;
import com.example.blue_hawk.domain.entity.matchparticipant.MatchParticipant;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateMatchParticipantUseCaseImpl implements CreateMatchParticipantUseCase {

    private final IMatchParticipantRepository matchParticipantRepository;

    public CreateMatchParticipantUseCaseImpl(IMatchParticipantRepository matchParticipantRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
    }

    @Override
    public CreateMatchParticipantOutput handle(CreateMatchParticipantCommand command) {
        MatchParticipant matchParticipant = new MatchParticipant(
                command.matchId(),
                command.userId()
        );

        MatchParticipant saved = matchParticipantRepository.save(matchParticipant);

        return new CreateMatchParticipantOutput(
                saved.getId().toString(),
                saved.getMatchId().toString(),
                saved.getUserId().toString()
        );
    }
}
