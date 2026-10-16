package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.GetBoardgameOutput;
import com.example.blue_hawk.application.dto.boardgame.GetBoardgameQuery;
import com.example.blue_hawk.domain.entity.boardgame.Boardgame;
import com.example.blue_hawk.domain.repository.IBoardgameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetBoardgameUseCaseImpl implements GetBoardgameUseCase {

    private final IBoardgameRepository boardgameRepository;

    public GetBoardgameUseCaseImpl(IBoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @Override
    public GetBoardgameOutput handle(GetBoardgameQuery query) {
        Boardgame boardgame = boardgameRepository
                .findById(UUID.fromString(query.id()))
                .orElseThrow(() -> new RuntimeException("Boardgame not found"));

        return new GetBoardgameOutput(
                boardgame.getId().toString(),
                boardgame.getName(),
                boardgame.getDescription(),
                boardgame.getReleaseYear(),
                boardgame.getPlayerNumber(),
                boardgame.getPlayTime()
        );
    }
}
