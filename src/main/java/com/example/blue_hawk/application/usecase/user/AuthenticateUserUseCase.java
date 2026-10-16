package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.AuthenticateUserCommand;
import com.example.blue_hawk.application.dto.user.AuthenticateUserOutput;

public interface AuthenticateUserUseCase {
    AuthenticateUserOutput handle(AuthenticateUserCommand command);
}
