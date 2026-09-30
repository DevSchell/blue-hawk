package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.CreateUserCommand;
import com.example.blue_hawk.application.dto.user.UserOutput;

public interface CreateUserUseCase {
    UserOutput handle(CreateUserCommand command);
}
