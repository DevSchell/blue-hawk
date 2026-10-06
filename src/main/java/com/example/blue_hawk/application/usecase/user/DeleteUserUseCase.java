package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.DeleteUserCommand;

public interface DeleteUserUseCase {
    void handle(DeleteUserCommand command);
}
