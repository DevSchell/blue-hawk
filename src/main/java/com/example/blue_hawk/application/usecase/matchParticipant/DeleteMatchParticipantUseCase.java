package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.DeleteMatchParticipantOutput;

public interface DeleteMatchParticipantUseCase {
    DeleteMatchParticipantOutput handle(DeleteMatchParticipantCommand command);
}
