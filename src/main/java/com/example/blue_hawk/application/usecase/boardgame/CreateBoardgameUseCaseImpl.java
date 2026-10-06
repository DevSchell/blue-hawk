package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.CreateBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.CreateBoardgameOutput;
import com.example.blue_hawk.domain.entity.boardgame.Boardgame;
import com.example.blue_hawk.domain.repository.IBoardgameRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBoardgameUseCaseImpl implements CreateBoardgameUseCase {

    private final IBoardgameRepository boardgameRepository;

    public CreateBoardgameUseCaseImpl(IBoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @Override
    public CreateBoardgameOutput handle(CreateBoardgameCommand command) {
        Boardgame boardgame = new Boardgame(
                command.name(),
                command.description(),
                command.releaseYear(),
                command.playerNumber(),
                command.playTime()
        );

        Boardgame saved = boardgameRepository.save(boardgame);

        return new CreateBoardgameOutput(
                saved.getId().toString(),
                saved.getName(),
                saved.getDescription(),
                saved.getReleaseYear(),
                saved.getPlayerNumber(),
                saved.getPlayTime()
        );
    }
}
