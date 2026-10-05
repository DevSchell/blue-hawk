package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.CreateMatchCommand;
import com.example.blue_hawk.application.dto.match.CreateMatchOutput;

public interface CreateMatchUseCase {
    CreateMatchOutput handle(CreateMatchCommand command);

}
