package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.CreateUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.CreateUserBoardgameOutput;
import com.example.blue_hawk.domain.entity.UserBoardgame;
import com.example.blue_hawk.domain.repository.IUserBoardGameRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUserBoardgameUseCaseImpl implements CreateUserBoardgameUseCase {

    private final IUserBoardGameRepository userBoardGameRepository;

    public CreateUserBoardgameUseCaseImpl(IUserBoardGameRepository userBoardGameRepository) {
        this.userBoardGameRepository = userBoardGameRepository;
    }

    @Override
    public CreateUserBoardgameOutput handle(CreateUserBoardgameCommand command) {
        UserBoardgame userBoardgame = new UserBoardgame(command.userId(), command.boardgameId());

        UserBoardgame savedUserBoardgame = userBoardGameRepository.save(userBoardgame);

        return new CreateUserBoardgameOutput(savedUserBoardgame.getId().toString(), savedUserBoardgame.getUserId().toString(), savedUserBoardgame.getBoardgameId().toString());
    }
}
