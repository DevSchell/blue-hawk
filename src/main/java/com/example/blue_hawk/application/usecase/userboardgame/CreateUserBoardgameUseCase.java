package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.CreateUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.CreateUserBoardgameOutput;

public interface CreateUserBoardgameUseCase {
    CreateUserBoardgameOutput handle(CreateUserBoardgameCommand command);
}
