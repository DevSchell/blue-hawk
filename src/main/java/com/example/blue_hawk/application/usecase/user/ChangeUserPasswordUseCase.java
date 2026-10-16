package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.ChangeUserPasswordCommand;

public interface ChangeUserPasswordUseCase {
    void handle(ChangeUserPasswordCommand command);
}
