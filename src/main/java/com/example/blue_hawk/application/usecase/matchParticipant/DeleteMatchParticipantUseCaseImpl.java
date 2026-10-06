package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantCommand;
import com.example.blue_hawk.domain.repository.IMatchParticipantRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteMatchParticipantUseCaseImpl implements DeleteMatchParticipantUseCase {

    private final IMatchParticipantRepository matchParticipantRepository;

    public DeleteMatchParticipantUseCaseImpl(IMatchParticipantRepository matchParticipantRepository) {
        this.matchParticipantRepository = matchParticipantRepository;
    }

    @Override
    public void handle(DeleteMatchParticipantCommand command) {
        UUID id = UUID.fromString(command.id());
        if (!matchParticipantRepository.existsById(id)) {
            throw new RuntimeException("MatchParticipant not found with id: " + command.id());
        }
        matchParticipantRepository.deleteById(id);
    }
}
