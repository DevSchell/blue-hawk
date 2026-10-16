package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.DeleteBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.DeleteBoardgameOutput;
import com.example.blue_hawk.domain.repository.IBoardgameRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteBoardgameUseCaseImpl implements DeleteBoardgameUseCase {

    private final IBoardgameRepository boardgameRepository;

    public DeleteBoardgameUseCaseImpl(IBoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @Override
    public DeleteBoardgameOutput handle(DeleteBoardgameCommand command) {
        boardgameRepository.deleteById(command.id());
        return new DeleteBoardgameOutput(command.id());
    }
}
