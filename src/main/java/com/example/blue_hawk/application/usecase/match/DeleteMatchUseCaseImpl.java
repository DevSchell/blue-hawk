package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.DeleteMatchCommand;
import com.example.blue_hawk.application.dto.match.DeleteMatchOutput;
import com.example.blue_hawk.domain.repository.IMatchRepository;

import org.springframework.stereotype.Service;

@Service

public class DeleteMatchUseCaseImpl implements  DeleteMatchUseCase{
    private final IMatchRepository matchRepository;

    public DeleteMatchUseCaseImpl(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public DeleteMatchOutput handle(DeleteMatchCommand command) {
        matchRepository.deleteById(command.id());

        return new DeleteMatchOutput((command.id()));
    }
}
