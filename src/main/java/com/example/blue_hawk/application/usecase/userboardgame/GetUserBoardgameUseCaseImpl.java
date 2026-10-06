package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.GetUserBoardgameOutput;
import com.example.blue_hawk.application.dto.userboardgame.GetUserBoardgameQuery;
import com.example.blue_hawk.domain.entity.userboardgame.UserBoardgame;
import com.example.blue_hawk.domain.repository.IUserBoardGameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserBoardgameUseCaseImpl implements GetUserBoardgameUseCase {

    private final IUserBoardGameRepository userBoardGameRepository;

    public GetUserBoardgameUseCaseImpl(IUserBoardGameRepository userBoardGameRepository) {
        this.userBoardGameRepository = userBoardGameRepository;
    }

    @Override
    public GetUserBoardgameOutput handle(GetUserBoardgameQuery query) {
        UserBoardgame userBoardgame = userBoardGameRepository
                .findById(UUID.fromString(query.id()))
                .orElseThrow(() -> new RuntimeException("UserBoardgame not found"));

        return new GetUserBoardgameOutput(
                userBoardgame.getId().toString(),
                userBoardgame.getUserId().toString(),
                userBoardgame.getBoardgameId().toString());
    }
}
