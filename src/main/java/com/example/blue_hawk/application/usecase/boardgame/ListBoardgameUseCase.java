package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.ListBoardgameOutput;
import com.example.blue_hawk.application.dto.boardgame.ListBoardgameQuery;

import java.util.List;

public interface ListBoardgameUseCase {
    List<ListBoardgameOutput> handle(ListBoardgameQuery query);
}
