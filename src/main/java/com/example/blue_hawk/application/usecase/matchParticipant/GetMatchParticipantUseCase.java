package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.GetMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.GetMatchParticipantQuery;

public interface GetMatchParticipantUseCase {

    GetMatchParticipantOutput handle(GetMatchParticipantQuery query);
}
