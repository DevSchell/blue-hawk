package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.CreateMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.CreateMatchParticipantOutput;

public interface CreateMatchParticipantUseCase {
    CreateMatchParticipantOutput handle(CreateMatchParticipantCommand command);
}
