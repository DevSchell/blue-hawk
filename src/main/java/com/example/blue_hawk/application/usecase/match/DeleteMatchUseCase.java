package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.DeleteMatchCommand;

public interface DeleteMatchUseCase {
    void handle(DeleteMatchCommand command);
}
