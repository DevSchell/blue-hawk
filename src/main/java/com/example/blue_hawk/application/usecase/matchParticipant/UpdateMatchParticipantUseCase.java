package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.UpdateMatchParticipantCommand;
import com.example.blue_hawk.application.dto.matchParticipant.UpdateMatchParticipantOutput;

public interface UpdateMatchParticipantUseCase {

    UpdateMatchParticipantOutput handle(UpdateMatchParticipantCommand command);
}
