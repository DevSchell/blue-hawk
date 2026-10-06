package com.example.blue_hawk.application.usecase.match;

import com.example.blue_hawk.application.dto.match.GetMatchOutput;
import com.example.blue_hawk.application.dto.match.GetMatchQuery;
import com.example.blue_hawk.domain.entity.match.Match;
import com.example.blue_hawk.domain.repository.IMatchRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetMatchUseCaseImpl implements GetMatchUseCase {

    private final IMatchRepository matchRepository;

    public GetMatchUseCaseImpl(IMatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public GetMatchOutput handle(GetMatchQuery query) {
        Match match = matchRepository
                .findById(UUID.fromString(query.id()))
                .orElseThrow(() -> new RuntimeException("Match not found with id: " + query.id()));

        return new GetMatchOutput(
                match.getId().toString(),
                match.getUserBoardgameId().toString(),
                match.getMaxUsers()
        );
    }
}
