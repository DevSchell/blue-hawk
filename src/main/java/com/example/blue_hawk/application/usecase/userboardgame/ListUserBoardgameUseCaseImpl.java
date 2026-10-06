package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.ListUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.ListUserBoardgameQuery;
import com.example.blue_hawk.domain.repository.IUserBoardGameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ListUserBoardgameUseCaseImpl implements ListUserBoardgameUseCase {

    private final IUserBoardGameRepository userBoardGameRepository;

    public ListUserBoardgameUseCaseImpl(IUserBoardGameRepository userBoardGameRepository) {
        this.userBoardGameRepository = userBoardGameRepository;
    }

    @Override
    public List<ListUserBoardgameOutput> handle(ListUserBoardgameQuery query) {
        UUID userId = query.userId() != null ? UUID.fromString(query.userId()) : null;
        UUID boardgameId = query.boardgameId() != null ? UUID.fromString(query.boardgameId()) : null;

        return userBoardGameRepository
                .findAll(userId, boardgameId, query.page(), query.size())
                .getContent()
                .stream()
                .map(ub -> new ListUserBoardgameOutput(
                        ub.getId().toString(),
                        ub.getUserId().toString(),
                        ub.getBoardgameId().toString()))
                .toList();
    }
}
