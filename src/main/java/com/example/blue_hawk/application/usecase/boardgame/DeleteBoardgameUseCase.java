package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.DeleteBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.DeleteBoardgameOutput;

public interface DeleteBoardgameUseCase {
    DeleteBoardgameOutput handle(DeleteBoardgameCommand command);
}
