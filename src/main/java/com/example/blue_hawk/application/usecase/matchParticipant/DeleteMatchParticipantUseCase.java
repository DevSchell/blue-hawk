package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantCommand;

public interface DeleteMatchParticipantUseCase {
    void handle(DeleteMatchParticipantCommand command);
}
