package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.UpdateUserCommand;
import com.example.blue_hawk.application.dto.user.UserOutput;
import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final IUserRepository userRepository;

    public UpdateUserUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserOutput handle(UpdateUserCommand command) {
        UUID userId = UUID.fromString(command.id());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (command.name() != null) {
            user.setName(command.name().trim());
        }
        if (command.email() != null) {
            String email = User.normalizeEmail(command.email());
            boolean takenByAnotherUser = userRepository.findByEmail(email)
                    .filter(existing -> !existing.getId().equals(userId))
                    .isPresent();
            if (takenByAnotherUser) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
            }
            user.setEmail(email);
        }

        return UserOutput.from(userRepository.save(user));
    }
}
