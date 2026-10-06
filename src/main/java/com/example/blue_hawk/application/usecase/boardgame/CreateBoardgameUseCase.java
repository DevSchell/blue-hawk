package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.CreateBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.CreateBoardgameOutput;

public interface CreateBoardgameUseCase {
    CreateBoardgameOutput handle(CreateBoardgameCommand command);
}
