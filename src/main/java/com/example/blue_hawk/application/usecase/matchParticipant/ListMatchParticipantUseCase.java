package com.example.blue_hawk.application.usecase.matchParticipant;

import java.util.List;

import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantOutput;
import com.example.blue_hawk.application.dto.matchParticipant.ListMatchParticipantQuery;

public interface ListMatchParticipantUseCase {
    List<ListMatchParticipantOutput> handle(ListMatchParticipantQuery query);
}
