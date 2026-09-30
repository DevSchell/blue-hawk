package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.GetUserQuery;
import com.example.blue_hawk.application.dto.user.UserOutput;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class GetUserUseCaseImpl implements GetUserUseCase {

    private final IUserRepository userRepository;

    public GetUserUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserOutput handle(GetUserQuery query) {
        return userRepository.findById(UUID.fromString(query.id()))
                .map(UserOutput::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }
}
