package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.CreateMatchCommand;
import com.example.blue_hawk.application.dto.match.CreateMatchOutput;
import com.example.blue_hawk.domain.entity.Match;
import com.example.blue_hawk.domain.repository.IMatchRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateMatchUseCaseImpl implements CreateMatchUseCase {

    private final IMatchRepository matchRepository;

    public CreateMatchUseCaseImpl(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public CreateMatchOutput handle(CreateMatchCommand command) {
        Match match = new Match(
                command.boardgameId(),
                command.userId()
        );

        Match saved = matchRepository.save(match);

        return new CreateMatchOutput(
                saved.getId().toString(),
                saved.getBoardgameId().toString(),
                saved.getUserId().toString()
        );
    }
}
