package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.UpdateUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.UpdateUserBoardgameOutput;
import com.example.blue_hawk.domain.entity.UserBoardgame;
import com.example.blue_hawk.domain.repository.IUserBoardGameRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUserBoardgameUseCaseImpl implements UpdateUserBoardgameUseCase {

    private final IUserBoardGameRepository userBoardGameRepository;

    public UpdateUserBoardgameUseCaseImpl(IUserBoardGameRepository userBoardGameRepository) {
        this.userBoardGameRepository = userBoardGameRepository;
    }

    @Override
    public UpdateUserBoardgameOutput handle(UpdateUserBoardgameCommand command) {
        UserBoardgame userBoardgame = userBoardGameRepository
                .findById(UUID.fromString(command.id()))
                .orElseThrow(() -> new RuntimeException("UserBoardgame not found"));

        userBoardgame.setUserId(UUID.fromString(command.userId()));
        userBoardgame.setBoardgameId(UUID.fromString(command.boardgameId()));

        UserBoardgame updated = userBoardGameRepository.save(userBoardgame);

        return new UpdateUserBoardgameOutput(
                updated.getId().toString(),
                updated.getUserId().toString(),
                updated.getBoardgameId().toString());
    }
}
