package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantOutput;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteMatchParticipantUseCaseImpl implements DeleteMatchParticipantUseCase {

    private final IMatchParticipantRepository matchParticipantRepository;

    public DeleteMatchParticipantUseCaseImpl(IMatchParticipantRepository matchParticipantRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
    }

    @Override
    public DeleteMatchParticipantOutput handle(DeleteMatchParticipantCommand command) {
        matchParticipantRepository.deleteById(command.id());

        return new DeleteMatchParticipantOutput(command.id());
    }
}
