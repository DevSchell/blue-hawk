package com.example.blue_hawk.application.usecase.userboardgame;

import java.util.List;

import com.example.blue_hawk.application.dto.userboardgame.ListUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.ListUserBoardgameQuery;

public interface ListUserBoardgameUseCase {
    List<ListUserBoardgameOutput> handle(ListUserBoardgameQuery query);
}
