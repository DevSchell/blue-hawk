package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.UpdateUserCommand;
import com.example.blue_hawk.application.dto.user.UserOutput;

public interface UpdateUserUseCase {
    UserOutput handle(UpdateUserCommand command);
}
