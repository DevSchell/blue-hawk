package com.example.blue_hawk.application.usecase.userboardgame;

import com.example.blue_hawk.application.dto.userboardgame.DeleteUserBoardgameCommand;
import com.example.blue_hawk.application.dto.userboardgame.DeleteUserBoardgameOutput;
import com.example.blue_hawk.domain.repository.IUserBoardGameRepository;

import org.springframework.stereotype.Service;

@Service
public class DeleteUserBoardgameUseCaseImpl implements DeleteUserBoardgameUseCase {

    private final IUserBoardGameRepository userBoardGameRepository;

    public DeleteUserBoardgameUseCaseImpl(IUserBoardGameRepository userBoardGameRepository) {
        this.userBoardGameRepository = userBoardGameRepository;
    }

    @Override
    public DeleteUserBoardgameOutput handle(DeleteUserBoardgameCommand command) {
        userBoardGameRepository.deleteById(command.id());

        return new DeleteUserBoardgameOutput(command.id());
    }

}
