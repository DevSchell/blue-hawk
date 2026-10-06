package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.DeleteMatchCommand;
import com.example.blue_hawk.domain.repository.IMatchRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteMatchUseCaseImpl implements DeleteMatchUseCase {

    private final IMatchRepository matchRepository;

    public DeleteMatchUseCaseImpl(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public void handle(DeleteMatchCommand command) {
        UUID id = UUID.fromString(command.id());
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match not found with id: " + command.id());
        }
        matchRepository.deleteById(id);
    }
}
