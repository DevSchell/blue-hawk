package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.UpdateUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.UpdateUserBoardgameOutput;

public interface UpdateUserBoardgameUseCase {

    UpdateUserBoardgameOutput handle(UpdateUserBoardgameCommand command);
}
