package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.DeleteMatchCommand;
import com.example.blue_hawk.application.dto.match.DeleteMatchOutput;
public interface DeleteMatchUseCase {
    DeleteMatchOutput handle(DeleteMatchCommand command);
}
