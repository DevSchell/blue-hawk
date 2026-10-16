package com.example.blue_hawk.application.usecase.matchParticipant;

import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantQuery;

import java.util.List;

public interface ListMatchParticipantUseCase {
    List<ListMatchParticipantOutput> handle(ListMatchParticipantQuery query);
}
