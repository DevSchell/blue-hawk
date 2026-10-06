package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.UpdateBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.UpdateBoardgameOutput;
import com.example.blue_hawk.domain.entity.Boardgame;
import com.example.blue_hawk.domain.repository.IBoardgameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateBoardgameUseCaseImpl implements UpdateBoardgameUseCase {

    private final IBoardgameRepository boardgameRepository;

    public UpdateBoardgameUseCaseImpl(IBoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @Override
    public UpdateBoardgameOutput handle(UpdateBoardgameCommand command) {
        Boardgame boardgame = boardgameRepository
                .findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("Boardgame not found"));

        boardgame.setName(command.name());
        boardgame.setDescription(command.description());
        boardgame.setReleaseYear(command.releaseYear());
        boardgame.setPlayerNumber(command.playerNumber());
        boardgame.setPlayTime(command.playTime());

        Boardgame updated = boardgameRepository.save(boardgame);

        return new UpdateBoardgameOutput(
                updated.getId().toString(),
                updated.getName(),
                updated.getDescription(),
                updated.getReleaseYear(),
                updated.getPlayerNumber(),
                updated.getPlayTime()
        );
    }
}
