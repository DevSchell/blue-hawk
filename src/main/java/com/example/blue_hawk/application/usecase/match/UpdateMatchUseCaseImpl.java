package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.UpdateMatchCommand;
import com.example.blue_hawk.application.dto.match.UpdateMatchOutput;
import com.example.blue_hawk.domain.entity.Match;
import com.example.blue_hawk.domain.repository.IMatchRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateMatchUseCaseImpl implements UpdateMatchUseCase {

    private final IMatchRepository matchRepository;

    public UpdateMatchUseCaseImpl(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public UpdateMatchOutput handle(UpdateMatchCommand command) {
        Match match = matchRepository
                .findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + command.id()));

        match.setBoardgameId(UUID.fromString(command.boardgameId()));
        match.setUserId(UUID.fromString(command.userId()));
        match.setMaxUsers(command.maxUsers());

        Match updated = matchRepository.save(match);

        return new UpdateMatchOutput(
                updated.getId().toString(),
                updated.getBoardgameId().toString(),
                updated.getUserId().toString(),
                updated.getMaxUsers()
        );
    }
}
