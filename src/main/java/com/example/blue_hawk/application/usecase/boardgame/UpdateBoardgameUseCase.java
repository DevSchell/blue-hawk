package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.UpdateBoardgameCommand;
import com.example.blue_hawk.application.dto.boardgame.UpdateBoardgameOutput;

public interface UpdateBoardgameUseCase {
    UpdateBoardgameOutput handle(UpdateBoardgameCommand command);
}
