package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.UpdateMatchCommand;
import com.example.blue_hawk.application.dto.match.UpdateMatchOutput;

public interface UpdateMatchUseCase {
    UpdateMatchOutput handle(UpdateMatchCommand command);
}
