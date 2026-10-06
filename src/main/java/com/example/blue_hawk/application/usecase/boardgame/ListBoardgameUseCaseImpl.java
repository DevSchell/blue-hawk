package com.example.blue_hawk.application.usecase.boardgame;

import com.example.blue_hawk.application.dto.boardgame.ListBoardgameOutput;
import com.example.blue_hawk.application.dto.boardgame.ListBoardgameQuery;
import com.example.blue_hawk.domain.repository.IBoardgameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListBoardgameUseCaseImpl implements ListBoardgameUseCase {

    private final IBoardgameRepository boardgameRepository;

    public ListBoardgameUseCaseImpl(IBoardgameRepository boardgameRepository) {
        this.boardgameRepository = boardgameRepository;
    }

    @Override
    public List<ListBoardgameOutput> handle(ListBoardgameQuery query) {
        String name = query.name() != null && !query.name().isBlank() ? query.name() : null;
        int page = query.page() != null ? query.page() : 0;
        int size = query.size() != null ? query.size() : 10;

        return boardgameRepository
                .findAll(name, page, size)
                .getContent()
                .stream()
                .map(bg -> new ListBoardgameOutput(
                        bg.getId().toString(),
                        bg.getName(),
                        bg.getDescription(),
                        bg.getReleaseYear(),
                        bg.getPlayerNumber(),
                        bg.getPlayTime()))
                .toList();
    }
}
