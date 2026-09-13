package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.DeleteUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.DeleteUserBoardgameOutput;

public interface DeleteUserBoardgameUseCase {
    DeleteUserBoardgameOutput handle(DeleteUserBoardgameCommand command);
}
