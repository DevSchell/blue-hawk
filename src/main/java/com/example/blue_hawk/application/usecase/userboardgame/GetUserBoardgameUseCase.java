package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.GetUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.GetUserBoardgameQuery;

public interface GetUserBoardgameUseCase {

    GetUserBoardgameOutput handle(GetUserBoardgameQuery query);
}
