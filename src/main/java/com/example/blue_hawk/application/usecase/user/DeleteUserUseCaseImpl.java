package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.DeleteUserCommand;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final IUserRepository userRepository;

    public DeleteUserUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(DeleteUserCommand command) {
        UUID userId = UUID.fromString(command.id());
        if (!userRepository.existsById(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        // Exclusão física: remove o registro da tabela.
        userRepository.deleteById(userId);
    }
}
